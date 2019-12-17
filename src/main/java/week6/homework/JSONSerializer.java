package week9.homework;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class JSONSerializer extends SerializerImpl {

    @Override
    protected void elemPostprocessing(StringBuilder writer, StringBuilder tabCount) {
        writer.append(tabCount.toString())
                .append("}");
    }

    @Override
    protected void elemProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i) {
        try {
            try {
                writer.append(tabCount.toString())
                        .append("\"")
                        .append(fields[i].getName())
                        .append("\"")
                        .append(":")
                        .append(serialize(fields[i].get(obj), new StringBuilder(), new StringBuilder().append(tabCount).append("   ")));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i < fields.length - 1) writer.append(",");
        writer.append("\n");
    }

    @Override
    protected void elemPreprocessing(StringBuilder writer, StringBuilder tabCount) {
        writer.append(tabCount.toString())
                .append("{")
                .append("\n");
    }

    @Override
    protected boolean nullProcessing(Object obj, StringBuilder writer) {
        if (obj == null) {
            writer.append("\"").append("null").append("\"");
            return true;
        }
        return false;
    }

    @Override
    protected boolean collectonProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (isCollection(obj)) {
            writer.append(printCollection((Collection) obj, tabCount));
            return true;
        }
        return false;
    }


    @Override
    protected boolean arrayProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (isArray(obj)) {
            writer.append(printArray(obj, tabCount));
            return true;
        }
        return false;
    }

    @Override
    protected boolean wrapperProcessing(Object obj, StringBuilder writer) {
        if (isWrapper(obj)) {
            writer.append("\"").append(obj).append("\"");
            return true;
        }
        return false;
    }


    @Override
    protected String printArray(Object o, StringBuilder tabCount) throws IOException {
        StringBuilder writer = new StringBuilder();
        writer.append(tabCount).append("[").append("\n");

        int length = Array.getLength(o);
        for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(o, i);
            try {
                writer.append(tabCount).append(serialize(arrayElement, new StringBuilder(), tabCount));
                if (i < length - 1) writer.append(",");
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        writer.append(tabCount).append("]");
        return writer.toString();
    }

    @Override
    protected String printCollection(Collection o, StringBuilder tabCount) throws IOException {
        StringBuilder writer = new StringBuilder();
        writer.append(tabCount).append("[");
        for (Object o1 : o) {
            try {
                writer.append(tabCount).append(serialize(o1, new StringBuilder(), new StringBuilder()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.append(tabCount).append("]");
        return writer.toString();
    }

    @Override
    public String serialize(Object o) {
        String answer = "";
        try {
            answer = serialize(o, new StringBuilder(), new StringBuilder());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
