package week9.homework;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

public class XMLSerializer extends SerializerImpl {

    @Override
    protected void elemProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i) {
        try {
            writer.append(tabCount)
                    .append("<")
                    .append(fields[i].getName())
                    .append(">")
                    .append(serialize(fields[i].get(obj), new StringBuilder(), new StringBuilder().append(tabCount).append("   ")));
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            if (!isWrapper(fields[i].get(obj))) writer.append(tabCount);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        writer.append("</")
                .append(fields[i].getName())
                .append(">")
                .append("\n");
    }

    @Override
    protected void elemPreprocessing(StringBuilder writer, StringBuilder tabCount) {
        writer.append("\n");
    }

    @Override
    protected boolean nullProcessing(Object obj, StringBuilder writer) {
        if (obj == null) {
            writer.append("null");
            return true;
        }
        return false;
    }

    @Override
    protected boolean collectonProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (obj instanceof Collection) {
            writer.append(printCollection((Collection) obj, tabCount));
            return true;
        }
        return false;
    }

    @Override
    protected boolean arrayProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (obj.getClass().isArray()) {
            writer.append(printArray(obj, tabCount));
            return true;
        }
        return false;
    }

    @Override
    protected boolean wrapperProcessing(Object obj, StringBuilder writer) {
        if (isWrapper(obj)) {
            writer.append(obj);
            return true;
        }
        return false;
    }


    @Override
    protected String printArray(Object o, StringBuilder tabCount) throws IOException {
        StringBuilder writer = new StringBuilder();
        writer.append(tabCount).append("    ").append("\n");

        int length = Array.getLength(o);
        for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(o, i);
            try {
                writer.append(tabCount)
                        .append("<")
                        .append("element")
                        .append(">")
                        .append(serialize(arrayElement, new StringBuilder(), tabCount))
                        .append("</")
                        .append("element")
                        .append(">")
                        .append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return writer.toString();
    }

    @Override
    protected String printCollection(Collection o, StringBuilder tabCount) throws IOException {
        StringBuilder writer = new StringBuilder();
        writer.append(tabCount).append("    ").append("\n");
        for (Object o1 : o) {
            try {
                writer.append(tabCount)
                        .append("<")
                        .append("element")
                        .append(">")
                        .append(serialize(o1, new StringBuilder(), tabCount))
                        .append("</")
                        .append("element")
                        .append(">")
                        .append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.append(tabCount).append("]");
        return writer.toString();
    }

    @Override
    public String serialize(Object o) {
        StringBuilder writer = new StringBuilder();
        try {

            writer.append(("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"))
                    .append("<")
                    .append(o.getClass().getSimpleName())
                    .append(">")
                    .append(serialize(o, new StringBuilder(), new StringBuilder().append("   ")))
                    .append("</")
                    .append(o.getClass().getSimpleName())
                    .append(">")
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    @Override
    protected void elemPostprocessing(StringBuilder writer, StringBuilder tabCount) {
    }
}
