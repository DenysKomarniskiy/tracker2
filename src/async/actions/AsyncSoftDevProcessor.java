package async.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.AsyncContext;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.google.gson.Gson;
import com.scc.softdev.services.PairArray;
import com.scc.softdev.services.TTestCase;
import com.scc.softdev.services.TTestRun;
import com.scc.softdev.services.TTestStep;
import com.scc.softdev.services.UserField;
import com.scc.softdev.services.UserFieldArray;
import com.scc.softdev.services.impl.SDException;
import com.scc.softdev.services.impl.SoftDevTestCase;
import com.scc.softdev.services.impl.TestCaseImplService;

import models.entities.TestingSheet;
import models.entities.User;
import utils.HeaderHandlerResolver;

public class AsyncSoftDevProcessor implements Runnable {

	private AsyncContext asyncContext;
	private String action;
	private TestingSheet sheetTc;
	private User user;

	public AsyncSoftDevProcessor(AsyncContext asyncCtx, TestingSheet sheetTc, User user) {
		this.asyncContext = asyncCtx;
		this.action = "pass-tc";
		this.sheetTc = sheetTc;
		this.user = user;
	}

	@Override
	public void run() {
		System.out.println("AsyncSoftDevProcessor: start run");
		TTestRun tTestRun;		
		try {
			PrintWriter responseWriter = asyncContext.getResponse().getWriter();			
			switch (action) {
			case "pass-tc":				
				tTestRun = passTc();
				responseWriter.println((new Gson()).toJson(tTestRun));
				break;

			default:
				responseWriter.println("{\"error\":\"AsyncSoftDevProcessor: unknown action\"}");
				break;
			}
		} catch (Exception e) {											
			try {
				e.printStackTrace(asyncContext.getResponse().getWriter());				
			} catch (IOException e1) {				
				e1.printStackTrace();
			}			
		}

		asyncContext.complete();
	}

	private TTestRun passTc() throws SDException, Exception {

		String tc_id = sheetTc.getStorageTC().getTc_id();
		String[] splittedId = tc_id.split(":");
		tc_id = splittedId.length == 1 ? splittedId[0] : splittedId[1];
		TTestCase tTestCase;
		TTestRun tRun = null;
		long testsetId;
		long testRunId;

		TestCaseImplService service = new TestCaseImplService();
		HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver(user.getId(), user.getSdEncPass());
		service.setHandlerResolver(handlerResolver);
		SoftDevTestCase port = service.getTestCaseImplPort();
		
//		System.out.println(asyncContext.getRequest().getServletContext().getInitParameter("testCasePrefix") + tc_id);

		tTestCase = port.getTestCaseByUFI(asyncContext.getRequest().getServletContext().getInitParameter("testCasePrefix") + tc_id);
		
		if (tTestCase == null)
			throw new Exception("STC-TC-" + tc_id +" not found in SoftDev");
		
		
		PairArray testSets = port.getTestSetByName(
				sheetTc.getStorageTC().getTestSet().getSdSet(),  
				Long.valueOf(asyncContext.getRequest().getServletContext().getInitParameter("SDProductId")), 
				"Reviewed", 
				true
		);

		testsetId = (Long) testSets.getItem().get(0).getLeft();
				
		UserField tqcver = new UserField();
		tqcver.setNumber(1);
		tqcver.setName("TQC_Version_Tested");
		tqcver.setValue(sheetTc.getTqcVer());
		
		UserField env = new UserField();
		env.setNumber(2);
		env.setName("Environment");
		env.setValue(sheetTc.getTesting().getEnvironment());
		
		UserField runtime = new UserField();
		runtime.setNumber(3);
		runtime.setName("Actual_Run_Time");
		runtime.setValue(String.valueOf(sheetTc.getDuration()));
		
		UserField labver = new UserField();
		labver.setNumber(4);
		labver.setName("Lab_Version_Tested");
		labver.setValue(sheetTc.getLabVer());
		
		UserField genever = new UserField();
		genever.setNumber(7);
		genever.setName("Gene_Version_Tested");
		genever.setValue(sheetTc.getGeneVer());
		
		UserFieldArray userFields = new UserFieldArray(); 		
		userFields.getItem().add(tqcver);
		userFields.getItem().add(env);
		userFields.getItem().add(runtime);
		userFields.getItem().add(labver);
		userFields.getItem().add(genever);

		testRunId = port.startTestRun(tTestCase.getEntityID(), testsetId);
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		XMLGregorianCalendar executionDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);		
		
		port.updateTestRun(testRunId, userFields, executionDate, Long.valueOf(0), "");
			
		for (TTestStep tstep: tTestCase.getTestStep()){
			port.updateTestRunStepByStepId(testRunId, tstep.getStepId(), "Passed", "", executionDate);
		}
		
		tRun = port.getTestRun(testRunId);
		
		return tRun;
	}

}
