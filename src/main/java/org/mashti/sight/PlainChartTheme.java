/**
 * This file is part of sight.
 *
 * sight is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * sight is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with sight.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mashti.sight;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;

import static java.awt.Color.BLACK;

public class PlainChartTheme extends StandardChartTheme implements ChartTheme {

    private static final Font LARGE_FONT = new Font("Myriad Pro", Font.PLAIN, 25);
    private static final Font SMALL_FONT = LARGE_FONT.deriveFont(12f);
    private static final Font MEDIUM_FONT = SMALL_FONT.deriveFont(14f);
    private static final String[] GOOGLE_CHART_COLOR_SEQUENCE = {"0x3366cc", "0xdc3912", "0xff9900", "0x109618", "0x990099", "0x0099c6", "0xdd4477", "0x66aa00", "0xb82e2e", "0x316395", "0x994499", "0x22aa99", "0xaaaa11", "0x6633cc", "0xe67300", "0x8b0707", "0x651067", "0x329262", "0x5574a6",
                    "0x3b3eac", "0xb77322", "0x16d620", "0xb91383", "0xf4359e", "0x9c5935", "0xa9c413", "0x2a778d", "0x668d1c", "0xbea413", "0x0c5922", "0x743411"};
    private static final Paint[] PAINT_SEQUENCE = new Paint[GOOGLE_CHART_COLOR_SEQUENCE.length];
    static {
        int i = 0;
        for (String p : GOOGLE_CHART_COLOR_SEQUENCE) {
            PAINT_SEQUENCE[i] = Color.decode(p);
            i++;
        }
    }
    private static final PlainChartTheme INSTANCE = new PlainChartTheme();

    public PlainChartTheme() {

        super("plain");
        setDrawingSupplier(new DefaultDrawingSupplier(PAINT_SEQUENCE, new Paint[]{BLACK}, new Stroke[]{new BasicStroke(2.0f)}, new Stroke[]{new BasicStroke(1f)}, DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
    }

    public static void applyTheme(JFreeChart chart) {

        INSTANCE.apply(chart);
    }

    @Override
    public DrawingSupplier getDrawingSupplier() {

        return super.getDrawingSupplier();
    }

    @Override
    public void apply(final JFreeChart chart) {

        super.apply(chart);
        chart.getTitle().setFont(LARGE_FONT);
        final LegendTitle legend = chart.getLegend();
        decorateLegend(legend);

        final Plot plot = chart.getPlot();

        decoratePlot(plot);
        if (plot instanceof XYPlot) {
            final XYPlot xy_plot = chart.getXYPlot();
            decorateXYPlot(xy_plot);
        }
        else if (plot instanceof CategoryPlot) {
            final CategoryPlot category_plot = chart.getCategoryPlot();
            decorateCategoryPlot(category_plot);
        }

    }

    private void decorateCategoryPlot(final CategoryPlot category_plot) {

        final CategoryAxis domain_axis = category_plot.getDomainAxis();
        final ValueAxis range_axis = category_plot.getRangeAxis();
        decorateAxis(domain_axis);

        decorateAxis(range_axis);
        setTickUnit(range_axis);

        domain_axis.setMaximumCategoryLabelLines(3);

        category_plot.setDomainGridlinesVisible(false);
        category_plot.setRangeGridlinesVisible(false);

        final CategoryItemRenderer renderer = category_plot.getRenderer();
        renderer.setBaseOutlinePaint(BLACK);
        renderer.setBaseItemLabelPaint(BLACK);
        renderer.setBasePaint(BLACK);

        if (renderer instanceof StatisticalBarRenderer) {
            StatisticalBarRenderer bar_renderer = (StatisticalBarRenderer) renderer;
            bar_renderer.setErrorIndicatorPaint(BLACK);
            bar_renderer.setItemMargin(0.02);
        }
    }

    private void decorateXYPlot(final XYPlot xy_plot) {

        final ValueAxis domain_axis = xy_plot.getDomainAxis();
        final ValueAxis range_axis = xy_plot.getRangeAxis();
        decorateAxis(domain_axis);
        decorateAxis(range_axis);

        setTickUnit(domain_axis);
        setTickUnit(range_axis);

        xy_plot.setDomainGridlinesVisible(false);
        xy_plot.setRangeGridlinesVisible(false);

        final XYItemRenderer renderer = xy_plot.getRenderer();
        renderer.setBaseOutlinePaint(BLACK);
        renderer.setBaseItemLabelPaint(BLACK);
        renderer.setBasePaint(BLACK);

        if (renderer instanceof XYErrorRenderer) {
            XYErrorRenderer xy_error_renderer = (XYErrorRenderer) renderer;
            xy_error_renderer.setErrorPaint(BLACK);
            xy_error_renderer.setErrorStroke(new BasicStroke(1f));
        }
    }

    private void decorateAxis(final Axis axis) {

        axis.setAxisLinePaint(BLACK);
        axis.setTickLabelFont(SMALL_FONT);
        axis.setTickLabelPaint(BLACK);
        axis.setTickMarkPaint(BLACK);
        axis.setLabelPaint(BLACK);
        axis.setLabelFont(MEDIUM_FONT);
    }

    private void setTickUnit(ValueAxis axis) {

        final TickUnits units = new TickUnits();
        final long unit = Math.round(Math.round(axis.getUpperBound()) * 0.1);
        units.add(new NumberTickUnit(unit < 10 ? unit < 5 ? unit < 1 ? 0.5 : 1 : unit - unit % 5 : unit - unit % 10));
        axis.setStandardTickUnits(units);
    }

    private void decoratePlot(final Plot plot) {

        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(Color.WHITE);
    }

    private void decorateLegend(final LegendTitle legend) {

        if (legend != null) {
            legend.setItemFont(SMALL_FONT);
            legend.setBorder(0, 0, 0, 0);
            legend.setItemPaint(BLACK);
        }
    }
}
