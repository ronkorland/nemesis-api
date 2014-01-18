package com.nemesis.api.data.chart;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.nemesis.api.constants.Status;

@XmlRootElement
public class PieChart {

	private Title title;
	private Tooltip tooltip;
	private PlotOptionsPie plotOptions;
	private List<SeriesPie> series;

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public PlotOptionsPie getPlotOptions() {
		return plotOptions;
	}

	public void setPlotOptions(PlotOptionsPie plotOptions) {
		this.plotOptions = plotOptions;
	}

	public List<SeriesPie> getSeries() {
		return series;
	}

	public void setSeries(List<SeriesPie> series) {
		this.series = series;
	}

	public static PieChart convertToPieChart(LastActivity activity,
			String name, String titleText) {
		PieChart chart = new PieChart();
		Title title = new Title();
		title.setText(titleText);
		Pie pie = new Pie();
		pie.setAllowPointSelect(false);
		pie.setCursor("");
		pie.setShowInLegend(true);
		DataLabels dataLabels = new DataLabels();
		dataLabels.setConnectorColor("#000000");
		dataLabels.setEnabled(false);
		pie.setDataLabels(dataLabels);
		PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setPie(pie);

		SeriesPie serie = new SeriesPie();
		serie.setName(name);
		serie.setType("pie");

		List<DataPie> datas = new ArrayList<DataPie>();

		DataPie data = new DataPie();
		data.setName(Status.SUCCESS.name());
		data.setY(activity.getAmountOfSuccess());
		data.setSelected(false);
		data.setSliced(false);
		data.setColor("green");
		datas.add(data);

		data = new DataPie();
		data.setName(Status.FAILURE.name());
		data.setSelected(false);
		data.setSliced(false);
		data.setY(activity.getAmountOfFailed());
		data.setColor("red");
		datas.add(data);

		serie.setData(datas);

		List<SeriesPie> series = new ArrayList<SeriesPie>();
		series.add(serie);

		Tooltip tooltip = new Tooltip();
		tooltip.setPointFormat("Percentage: {point.percentage:.1f} %<br/>Amount: {point.y}");

		chart.setPlotOptions(plotOptions);
		chart.setSeries(series);
		chart.setTitle(title);
		chart.setTooltip(tooltip);
		return chart;
	}
}
