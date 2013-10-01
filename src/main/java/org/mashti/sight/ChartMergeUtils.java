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
