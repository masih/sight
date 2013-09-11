package org.mashti.sight;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

/** @author Masih Hajiarabderkani (mh638@st-andrews.ac.uk) */
public class ChartMergeUtils {

    public static JFreeChart merge(JFreeChart... charts) {

        final JFreeChart merge;
        if (charts == null || charts.length == 0) {
            merge = null;
        }
        else if (charts.length == 1) {
            merge = charts[0];
        }
        else {
            merge = null;

            for (JFreeChart chart : charts) {
                final Plot plot = chart.getPlot();
                if (plot instanceof XYPlot) {
                    final XYPlot xy = (XYPlot) plot;

                    final XYDataset dataset = xy.getDataset();

                }
            }
        }

        return merge;
    }
}
