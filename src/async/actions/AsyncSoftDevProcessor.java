package async.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.AsyncContext;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.scc.softdev.services.PairArray;
import com.scc.softdev.services.SDException;
import com.scc.softdev.services.TIssue;
import com.scc.softdev.services.TIssueArray;
import com.scc.softdev.services.TTestCase;
import com.scc.softdev.services.TTestRun;
import com.scc.softdev.services.TTestStep;
import com.scc.softdev.services.UserField;
import com.scc.softdev.services.UserFieldArray;
import com.scc.softdev.services.impl.EntityImplService;
import com.scc.softdev.services.impl.IssueImplService;
import com.scc.softdev.services.impl.SoftDevEntity;
import com.scc.softdev.services.impl.SoftDevIssue;
import com.scc.softdev.services.impl.SoftDevTestCase;
import com.scc.softdev.services.impl.TestCaseImplService;

import models.entities.FailInfo;
import models.entities.TestingSheet;
import models.entities.User;
import net.java.dev.jaxb.array.StringArray;
import utils.HeaderHandlerResolver;

public class AsyncSoftDevProcessor implements Runnable {

	private AsyncContext asyncContext;
	private String action;
	private TestingSheet sheetTc;
	private SoftDevTestCase testCasePort;
	private SoftDevEntity entityPort = null;
	private SoftDevIssue issuePort = null;

	public AsyncSoftDevProcessor(AsyncContext asyncCtx, TestingSheet sheetTc, User user) {
		this.asyncContext = asyncCtx;
		this.action = "process-tc";
		this.sheetTc = sheetTc;

		TestCaseImplService testCaseService = new TestCaseImplService();

		HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver(user.getId(), user.getSdEncPass());
		testCaseService.setHandlerResolver(handlerResolver);

		testCasePort = testCaseService.getTestCaseImplPort();

		if (sheetTc.getTcStatus().equals("F")) {
			EntityImplService entityService = new EntityImplService();
			IssueImplService issueService = new IssueImplService();

			entityService.setHandlerResolver(handlerResolver);
			issueService.setHandlerResolver(handlerResolver);

			entityPort = entityService.getEntityImplPort();
			issuePort = issueService.getIssueImplPort();
		}
	}

	public AsyncSoftDevProcessor(AsyncContext asyncCtx, String action) {
		this.asyncContext = asyncCtx;
		this.action = action;
	}

	@Override
	public void run() {
		System.out.println("AsyncSoftDevProcessor: start run");

		try {
			PrintWriter responseWriter = asyncContext.getResponse().getWriter();
			switch (action) {
			case "process-tc1":
				// entityPort.getEntityLinks(22L, 10000105581L, false)
				responseWriter.println((new Gson()).toJson(getSDTestCase()));
				break;

			case "process-tc":
				TTestRun tTestRun = null;
				TTestCase tTestCase = getSDTestCase();
				long testsetId = getSDTestSetId();
				UserFieldArray userFields = createSDUserFields();

				if (sheetTc.getTcStatus().equals("P")) {
					tTestRun = passTc(tTestCase, testsetId, userFields);
				} else if (sheetTc.getTcStatus().equals("F")) {
					tTestRun = failTc(tTestCase, testsetId, userFields);
				}

				if (tTestRun.getStatus().equals("Passed") || tTestRun.getStatus().equals("Failed")) {
					SessionFactory sessionFactory = (SessionFactory) asyncContext.getRequest().getServletContext().getAttribute("HibernateSessionFactory");
					Session hibernateSession = sessionFactory.getCurrentSession();
					Transaction tx = hibernateSession.beginTransaction();
					sheetTc.setSoftdev(1);
					hibernateSession.update(sheetTc);
					tx.commit();
				}

				responseWriter.println((new Gson()).toJson(tTestRun));
				break;

			default:
				responseWriter.println("{\"error\":\"AsyncSoftDevProcessor: unknown action\"}");
				break;
			}
		} catch (Exception e) {
			try {
				e.printStackTrace();
				e.printStackTrace(asyncContext.getResponse().getWriter());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		asyncContext.complete();
	}

	private TTestRun failTc(TTestCase tTestCase, long testsetId, UserFieldArray userFields) throws Exception {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		XMLGregorianCalendar executionDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

		FailInfo failInfo = (new Gson()).fromJson(sheetTc.getFailInfo(), FailInfo.class);

		ArrayList<TIssue> sdIssues = getSDIssues(failInfo);

		long testRunId = testCasePort.startTestRun(tTestCase.getEntityID(), testsetId);

		testCasePort.updateTestRun(testRunId, userFields, executionDate, Long.valueOf(0), "");

		for (TTestStep tstep : tTestCase.getTestStep()) {

			String actualResult = failInfo.getSteps().get(tstep.getStepOrder());

			if (actualResult != null) {
				testCasePort.updateTestRunStepByStepId(testRunId, tstep.getStepId(), "Failed", actualResult, executionDate);
			} else {
				testCasePort.updateTestRunStepByStepId(testRunId, tstep.getStepId(), "Passed", "", executionDate);
			}
		}

		TTestRun testRun = testCasePort.getTestRun(testRunId);
		long testRunEntityId = testRun.getEntityID();
		long testRunEntityType = testRun.getEntityType();
		long entityLinkTypeId = 105;

		for (TIssue sdIssue : sdIssues) {
			entityPort.addEntityLink(sdIssue.getEntityType(), sdIssue.getEntityID(), testRunEntityType, testRunEntityId, "Issue linked to Run (Run ID: " + testRunId + ")", entityLinkTypeId);
			entityPort.addEntityLink(testRunEntityType, testRunEntityId, sdIssue.getEntityType(), sdIssue.getEntityID(), "Run linked to Issue (Run ID: " + testRunId + ")", entityLinkTypeId);
		}

		return testRun;
	}

	private TTestRun passTc(TTestCase tTestCase, long testsetId, UserFieldArray userFields) throws Exception {

		long testRunId = testCasePort.startTestRun(tTestCase.getEntityID(), testsetId);

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		XMLGregorianCalendar executionDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

		testCasePort.updateTestRun(testRunId, userFields, executionDate, Long.valueOf(0), "");

		for (TTestStep tstep : tTestCase.getTestStep()) {
			testCasePort.updateTestRunStepByStepId(testRunId, tstep.getStepId(), "Passed", "", executionDate);
		}
		TTestRun testRun = testCasePort.getTestRun(testRunId);

		return testRun;
	}

	private ArrayList<TIssue> getSDIssues(FailInfo failInfo) throws Exception {

		ArrayList<TIssue> sdIssues = new ArrayList<TIssue>();
		
		try {
			for (String issueUFI : failInfo.getIssues()) {
				StringArray arrayIssueUFI = new StringArray();
				arrayIssueUFI.getItem().add(asyncContext.getRequest().getServletContext().getInitParameter("issuePrefix") + issueUFI);

				TIssueArray sdIssue = issuePort.getIssuesByUFI(arrayIssueUFI);

				sdIssues.add(sdIssue.getItem().get(0));
			}
		} catch (Exception e) {
			throw new Exception("No issue found");
		}
		
		return sdIssues;
	}

	private TTestCase getSDTestCase() throws Exception {

		String tc_id = sheetTc.getStorageTC().getTc_id();
		String[] splittedId = tc_id.split(":");
		tc_id = splittedId.length == 1 ? splittedId[0] : splittedId[1];

		TTestCase tTestCase = testCasePort.getTestCaseByUFI(asyncContext.getRequest().getServletContext().getInitParameter("testCasePrefix") + tc_id);

		if (tTestCase == null)
			throw new Exception("STC-TC-" + tc_id + " not found in SoftDev");

		return tTestCase;
	}

	private Long getSDTestSetId() throws Exception {

		PairArray testSets = testCasePort.getTestSetByName(sheetTc.getStorageTC().getTestSet().getSdSet(), Long.valueOf(asyncContext.getRequest().getServletContext().getInitParameter("SDProductId")), "Reviewed", true);

		Long testsetId = (Long) testSets.getItem().get(0).getLeft();

		if (testsetId == null)
			throw new Exception("Test set " + sheetTc.getStorageTC().getTestSet().getSdSet() + " not found in SoftDev");

		return testsetId;
	}

	private UserFieldArray createSDUserFields() {

		UserFieldArray userFields = new UserFieldArray();

		UserField tqcver = new UserField();
		tqcver.setNumber(1);
		tqcver.setName("TQC_Version_Tested");
		tqcver.setValue(sheetTc.getTqcVer());
		userFields.getItem().add(tqcver);

		UserField env = new UserField();
		env.setNumber(2);
		env.setName("Environment");
		env.setValue(sheetTc.getEnv().getName());
		userFields.getItem().add(env);

		UserField runtime = new UserField();
		runtime.setNumber(3);
		runtime.setName("Actual_Run_Time");
		runtime.setValue(String.valueOf(sheetTc.getDuration()));
		userFields.getItem().add(runtime);

		if (sheetTc.getLabVer() != null) {
			UserField labver = new UserField();
			labver.setNumber(4);
			labver.setName("Lab_Version_Tested");
			labver.setValue(sheetTc.getLabVer());
			userFields.getItem().add(labver);
		}

		if (sheetTc.getGeneVer() != null) {
			UserField genever = new UserField();
			genever.setNumber(7);
			genever.setName("Gene_Version_Tested");
			genever.setValue(sheetTc.getGeneVer());
			userFields.getItem().add(genever);
		}

		return userFields;
	}

}
