package utils;

import java.awt.Color;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.internet.InternetAddress;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

import org.jfree.data.general.DefaultPieDataset;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.
 */
public class ChartFormationJFreeChart {

	public ChartFormationJFreeChart(String pathFile, String[] listOflabel, Long[] listOfCountTC) {
		super();
		this.pathFile = pathFile;
		this.listOflabel = listOflabel;
		this.listOfCountTC = listOfCountTC;
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
