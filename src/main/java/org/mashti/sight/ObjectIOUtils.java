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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.io.IOUtils;

/** @author Masih Hajiarabderkani (mh638@st-andrews.ac.uk) */
public final class ObjectIOUtils {

    private ObjectIOUtils() {

    }

    @SuppressWarnings("unchecked")
    public static <Value> Value read(File file) throws IOException, ClassNotFoundException {

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            return (Value) in.readObject();
        }
        finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static void write(Object value, File destination) throws IOException {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(destination));
            out.writeObject(value);
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }
}
