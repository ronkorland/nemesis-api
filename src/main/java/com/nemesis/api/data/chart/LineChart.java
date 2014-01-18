package com.nemesis.api.data.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.nemesis.api.constants.Status;

public class LineChart {

	private Title title;
	private XAxis xAxis;
	private YAxis yAxis;
	private Tooltip tooltip;
	private Legend legend;
	private List<SeriesLine> series;
	private PlotOptionsLine plotOptions;

	public PlotOptionsLine getPlotOptions() {
		return plotOptions;
	}

	public void setPlotOptions(PlotOptionsLine plotOptions) {
		this.plotOptions = plotOptions;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public XAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}

	public YAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Legend getLegend() {
		return legend;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
	}

	public static LineChart convertToLineChart(
			HashMap<LocalDate, LastActivity> map) {
		Map<LocalDate, LastActivity> treeMap = new TreeMap<LocalDate, LastActivity>(
				map);
		LineChart lineChart = new LineChart();

		Legend legend = new Legend();
		legend.setLayout("horizontal");
		legend.setAlign("center");
		legend.setBorderWidth(1);
		legend.setVerticalAlign("bottom");
		legend.setEnabled(true);

		Title title = new Title();
		title.setText("");
		title.setX(0);

		XAxis xAxis = new XAxis();

		List<LineData> dataAmountOfTests = new ArrayList<LineData>();
		List<LineData> dataAmountOfFailed = new ArrayList<LineData>();
		List<LineData> dataAmountOfSuccess = new ArrayList<LineData>();
		List<String> categories = new ArrayList<String>();

		for (Entry<LocalDate, LastActivity> entry : treeMap.entrySet()) {
			DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");

			String date = dtf.print(entry.getKey());
			categories.add(date);

			dataAmountOfTests
					.add(new LineData(
							entry.getValue().getAmountOfTests(),
							"/#/tests?minusDays=-1&pageSize=15&pageNumber=0&sortDir=desc&sortedBy=startTime&startDate="
									+ date));
			dataAmountOfFailed
					.add(new LineData(
							entry.getValue().getAmountOfFailed(),
							"/#/tests?minusDays=-1&status=FAILURE&pageSize=15&pageNumber=0&sortDir=desc&sortedBy=startTime&startDate="
									+ date));
			dataAmountOfSuccess
					.add(new LineData(
							entry.getValue().getAmountOfSuccess(),
							"/#/tests?minusDays=-1&status=SUCCESS&pageSize=15&pageNumber=0&sortDir=desc&sortedBy=startTime&startDate="
									+ date));
		}
		xAxis.setCategories(categories);

		YAxis yAxis = new YAxis();
		Title yTitle = new Title();
		yTitle.setText("Amount Of Tests");
		yAxis.setTitle(yTitle);
		List<PlotLines> plotLines = new ArrayList<PlotLines>();
		PlotLines plotLinesItem = new PlotLines();
		plotLinesItem.setColor("#808080");
		plotLinesItem.setValue(0);
		plotLinesItem.setWidth(1);
		plotLines.add(plotLinesItem);
		yAxis.setPlotLines(plotLines);

		Tooltip tooltip = new Tooltip();
		tooltip.setValueSuffix("");
		tooltip.setPointFormat("<span style='color:{series.color}'>{series.name}</span>: <b>{point.y}</b><br/>");

		List<SeriesLine> series = new ArrayList<SeriesLine>();

		SeriesLine serieTotal = new SeriesLine();
		serieTotal.setData(dataAmountOfTests);
		serieTotal.setName("TOTAL");
		serieTotal.setColor("blue");
		serieTotal.setIndex(1000);

		SeriesLine serieFailed = new SeriesLine();
		serieFailed.setData(dataAmountOfFailed);
		serieFailed.setName(Status.FAILURE.name());
		serieFailed.setColor("red");
		serieFailed.setIndex(800);

		SeriesLine serieSuccess = new SeriesLine();
		serieSuccess.setData(dataAmountOfSuccess);
		serieSuccess.setName(Status.SUCCESS.name());
		serieSuccess.setColor("green");
		serieSuccess.setIndex(900);

		series.add(serieTotal);

		series.add(serieSuccess);
		series.add(serieFailed);

		PlotOptionsLine plotOptions = new PlotOptionsLine();
		Line line = new Line();
		DataLabels dataLabels = new DataLabels();
		dataLabels.setEnabled(true);
		dataLabels.setFormat(null);
		line.setDataLabels(dataLabels);

		line.setEnableMouseTracking(true);
		plotOptions.setLine(line);

		PlotOptionsLineSeries plotOptionsSeries = new PlotOptionsLineSeries();
		Events events = new Events();
		Point point = new Point(events);
		plotOptionsSeries.setPoint(point);
		plotOptions.setSeries(plotOptionsSeries);

		lineChart.setLegend(legend);
		lineChart.setSeries(series);
		lineChart.setTitle(title);
		lineChart.setTooltip(tooltip);
		lineChart.setxAxis(xAxis);
		lineChart.setyAxis(yAxis);
		lineChart.setPlotOptions(plotOptions);

		return lineChart;
	}

	public List<SeriesLine> getSeries() {
		return series;
	}

	public void setSeries(List<SeriesLine> series) {
		this.series = series;
	}

}
