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
