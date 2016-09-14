package utils;

import java.awt.Color;
import java.awt.Font;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;

import org.jfree.data.general.DefaultPieDataset;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.
 */
public class ChartFormationJFreeChart {

	public ChartFormationJFreeChart(DataFromCurrentTestingTableDB data, String pathFile) {
		super();
		this.pathFile = pathFile;
		Long DurationNoNeedTC = data.getDurationNoNeedTC();
		Long DurationInvestigatingTC = data.getDurationInvestigatingTC();
		Long DurationCorrectionTC = data.getDurationCorrectionTC();
		Long DurationWaitingTC = data.getDurationWaitingTC();
		Long DurationFailedTC = data.getDurationFailedTC();
		Long DurationPassedTC = data.getDurationPassedTC();
		Long DurationEmptyTC = data.getDurationEmptyTC();

		Long CountNoNeedTC = data.getCountNoNeedTC();
		Long CountInvestigatingTC = data.getCountInvestigatingTC();
		Long CountCorrectionTC = data.getCountCorrectionTC();
		Long CountWaitingTC = data.getCountWaitingTC();
		Long CountFailedTC = data.getCountFailedTC();
		Long CountPassedTC = data.getCountPassedTC();
		Long CountEmptyTC = data.getCountEmptyTC();		

		String labelNoNeed = "NoNeed [" + DurationNoNeedTC + " min]";
		String labelInvestigating = "Investigating [" + DurationInvestigatingTC + " min]";
		String labelCorrection = "Correction [" + DurationCorrectionTC + " min]";
		String labelWaiting = "Waiting [" + DurationWaitingTC + " min]";
		String labelFailed = "Failed [" + DurationFailedTC + " min]";
		String labelPassed = "Passed [" + DurationPassedTC + " min]";
		String labelEmpty = "Empty [" + DurationEmptyTC + " min]";
		this.setListOflabel(new String[] { labelNoNeed, labelInvestigating, labelCorrection, labelWaiting, labelFailed, labelPassed, labelEmpty });
		this.setListOfCountTC(new Long[] { CountNoNeedTC, CountInvestigatingTC, CountCorrectionTC, CountWaitingTC, CountFailedTC, CountPassedTC, CountEmptyTC });
		
	}


	private String pathFile;
	private String[] listOflabel;
	private Long[] listOfCountTC;

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String value) {
		pathFile = value;
	}

	public String[] getListOflabel() {
		return listOflabel;
	}

	public void setListOflabel(String[] listOfValue) {
		listOflabel = listOfValue;
	}

	public Long[] getListOfCountTC() {
		return listOfCountTC;
	}

	public void setListOfCountTC(Long[] listOfValue) {
		listOfCountTC = listOfValue;
	}

	
	public void createChart() {

		String[] ListOflabel = getListOflabel();
		Long[] listOfCountTC = getListOfCountTC();
		DefaultPieDataset dataset = new DefaultPieDataset();

		for (int i = 0; i < ListOflabel.length; i++) {
			dataset.setValue(ListOflabel[i], listOfCountTC[i]);
		}

		JFreeChart chart = ChartFactory.createPieChart("", // chart title
				dataset, // data
				false, // include legend
				false, false);

		chart.setNotify(false);
		File file = new File(getPathFile());

		chart.setBackgroundPaint(Color.white);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setIgnoreZeroValues(true);

		plot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));

		plot.setNoDataMessage("No data available");
		plot.setCircular(true);
		plot.setBackgroundPaint(Color.white);
		plot.setBaseSectionOutlinePaint(Color.BLACK);
		plot.setSectionPaint(dataset.getIndex(ListOflabel[0]), Color.gray); //NoNeed
		plot.setSectionPaint(dataset.getIndex(ListOflabel[1]), Color.magenta);//Investigating
		plot.setSectionPaint(dataset.getIndex(ListOflabel[2]), Color.pink); //Correction
		plot.setSectionPaint(dataset.getIndex(ListOflabel[3]), Color.orange); //Waiting
		plot.setSectionPaint(dataset.getIndex(ListOflabel[4]), Color.red); //Failed
		plot.setSectionPaint(dataset.getIndex(ListOflabel[5]), Color.green); //Passed
		plot.setSectionPaint(dataset.getIndex(ListOflabel[6]), Color.white); //Empty

		plot.setMaximumLabelWidth(.3);
		plot.setCircular(true);

		try {
			ChartUtilities.saveChartAsPNG(file, chart, 600, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
