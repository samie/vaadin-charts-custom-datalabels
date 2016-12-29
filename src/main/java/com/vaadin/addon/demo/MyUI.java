package com.vaadin.addon.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/** Simple Vaadin UI
 *
 */
@Theme("valo")
public class MyUI extends UI {

	Chart chart = new Chart(ChartType.BAR);
	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout(chart);
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);

		Configuration conf = chart.getConfiguration();
		conf.setTitle("Hello Charts!");
		
		// Captions are static. Notice the off-by-one
		XAxis x = new XAxis();
        x.setCategories("", "One", "Two", "Three", "Four", "Five");
        x.setTitle((String) null);
        conf.addxAxis(x);
		
        // Data series from 1 to 5 with some generated values
		DataSeries data = new DataSeries("Data");		
		for (int i = 1; i < 6; i++) {
		   // Shoe size on the X-axis, age on the Y-axis
			DataSeriesItem item = new DataSeriesItem(
			         i,
			         i*2.1f);
			item.setName("Name for "+i); // We use name in formatter
 		    data.add(item);
		}
		DataLabels dataLabels = new DataLabels(true);
		dataLabels.setFormatter("'<b>'+ this.point.name +'</b>'");
		PlotOptionsBar plotOptions = new PlotOptionsBar();
		plotOptions.setDataLabels(dataLabels);
		data.setPlotOptions(plotOptions);
		conf.addSeries(data);

	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}