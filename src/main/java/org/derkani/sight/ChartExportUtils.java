/**
 * Copyright © 2015, Masih H. Derkani
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.derkani.sight;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.io.IOUtils;
import org.jfree.chart.JFreeChart;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 * @author Masih Hajiarabderkani (mh638@st-andrews.ac.uk)
 */
public final class ChartExportUtils {

    private static final String SVG_CHARSET = "UTF-8";
    private static final boolean USE_CSS_IN_SVG_EXPORT = true;
    private static final DOMImplementation DOM = GenericDOMImplementation.getDOMImplementation();

    public static void saveAsSVG(JFreeChart chart, int width, int height, File destination) throws IOException {

        final Document document = DOM.createDocument(null, "svg", null);
        final SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(destination), SVG_CHARSET);
            chart.draw(svgGenerator, new Rectangle(width, height));
            svgGenerator.stream(out, USE_CSS_IN_SVG_EXPORT);
            out.flush();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static void saveAsSerializedObject(JFreeChart chart, File destination) throws IOException {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(destination));
            out.writeObject(chart);
            out.flush();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
