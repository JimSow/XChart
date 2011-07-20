/**
 * Copyright 2011 Xeiam LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xeiam.xcharts;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.xeiam.xcharts.series.Series;
import com.xeiam.xcharts.series.SeriesColor;
import com.xeiam.xcharts.series.SeriesLineStyle;
import com.xeiam.xcharts.series.SeriesMarker;

/**
 * @author timmolter
 */
public class Chart {

    private int width;
    private int height;

    protected final static int CHART_PADDING = 10;

    private ChartTitle chartTitle = new ChartTitle(this);
    private ChartLegend chartLegend = new ChartLegend(this);
    private AxisPair axisPair = new AxisPair(this);
    private Plot plot = new Plot(this);

    /**
     * Constructor
     * 
     * @param pWidth
     * @param pHeight
     */
    public Chart(final int pWidth, final int pHeight) {
        width = pWidth;
        height = pHeight;
    }

    public void paint(final Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // global rendering hint
        g.setColor(ChartColor.getAWTColor(ChartColor.GREY));
        g.fillRect(0, 0, width, height);

        chartTitle.paint(g);
        chartLegend.paint(g);
        axisPair.paint(g);
        plot.paint(g);

        g.dispose();

        // reset static Ids
        SeriesColor.resetId();
        SeriesLineStyle.resetId();
        SeriesMarker.resetId();

    }

    // GETTERS & SETTERS

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    protected ChartTitle getTitle() {
        return chartTitle;
    }

    protected ChartLegend getLegend() {
        return chartLegend;
    }

    protected AxisPair getAxisPair() {
        return axisPair;
    }

    protected Plot getPlot() {
        return plot;
    }

    // EXTERNAL GETTERS & SETTERS

    public Series addSeries(String seriesName, double[] xData, double[] yData) {
        return axisPair.addSeries(seriesName, xData, yData);
    }

    public void setChartTitle(String title) {
        this.chartTitle.setText(title);
    }

    public void setChartTitleVisible(boolean isVisible) {
        this.chartTitle.setVisible(isVisible);
    }

    public void setXAxisTitle(String title) {
        this.axisPair.getXAxis().setAxisTitle(title);
    }

    public void setYAxisTitle(String title) {
        this.axisPair.getYAxis().setAxisTitle(title);
    }

    public void setAxisTitlesVisible(boolean isVisible) {
        this.axisPair.getXAxis().getAxisTitle().setVisible(isVisible);
        this.axisPair.getYAxis().getAxisTitle().setVisible(isVisible);
    }

    public void setChartLegendVisible(boolean isVisible) {
        this.chartLegend.setVisible(isVisible);
    }
}
