package utils;

import java.awt.Color;
import java.awt.Font;

import java.io.File;
import java.io.IOException;

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

	String PathFile;

	public ChartFormationJFreeChart(String PathFile) {
		// System.out.println("----------------------------------");
		// System.out.println(PathFile);

		this.PathFile = PathFile;

	}

	public void createChart() {

		String empty = "Empty [510 min]";
		String passed = "Passed [9999 min]";
		String waiting = "Waiting [50 min]";
		String failed = "Failed [1000 min]";
		String noNeed = "NoNeed [5 min]";
		String correction = "Correction [50 min]";

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(empty, 22);
		dataset.setValue(passed, 626);
		dataset.setValue(waiting, 20);
		dataset.setValue(failed, 84);
		dataset.setValue(noNeed, 2);
		dataset.setValue(correction, 320);
		JFreeChart chart = ChartFactory.createPieChart("", // chart title
				dataset, // data
				false, // include legend
				false, false);
		// chart.setBorderPaint(Color.white);
		chart.setNotify(false);
		File file = new File(this.PathFile);
		//File file = new File(this.PathFile + "\\pie_chart.png");
		chart.setBackgroundPaint(Color.white);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setIgnoreZeroValues(true);

		plot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));

		plot.setNoDataMessage("No data available");
		plot.setCircular(true);
		plot.setBackgroundPaint(Color.white);
		plot.setBaseSectionOutlinePaint(Color.BLACK);
		plot.setSectionPaint(dataset.getIndex(empty), Color.white);
		plot.setSectionPaint(dataset.getIndex(passed), Color.green);
		plot.setSectionPaint(dataset.getIndex(waiting), Color.orange);
		plot.setSectionPaint(dataset.getIndex(failed), Color.red);
		plot.setSectionPaint(dataset.getIndex(noNeed), Color.gray);
		plot.setSectionPaint(dataset.getIndex(correction), Color.pink);

		plot.setMaximumLabelWidth(.3);
		plot.setCircular(true);

		plot.setBaseSectionPaint(Color.GREEN);

		try {
			ChartUtilities.saveChartAsPNG(file, chart, 600, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
