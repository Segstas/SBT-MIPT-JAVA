package week9.homework;


import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

public class SerializerImpl {
    public SerializerImpl() {
    }

    public void setStrategy(Serializer serializer) {
        this.serializer = serializer;
    }

    Serializer serializer;

    Set<Class<?>> wrappers = Wrappers.wrapperSet;

    public SerializerImpl(Serializer serializer) {
        this.serializer = serializer;
    }

    boolean isCollection(Object obj) {
        return obj instanceof Collection;
    }

    private boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    private boolean isWrapper(Object obj) {
        return wrappers.contains(obj);
    }


    public String serialize(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (nullProcessing(obj, writer)) return writer.toString();
        if (wrapperProcessing(obj, writer)) return writer.toString();
        if (arrayProcessing(obj, writer, tabCount)) return writer.toString();
        if (collectonProcessing(obj, writer, tabCount)) return writer.toString();
        Class clazz = obj.getClass();
        serializer.elemPreprocessing(writer, tabCount);
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            elemProcessing(obj, writer, tabCount, fields, i);
        }
        serializer.elemPostprocessing(writer, tabCount);
        return writer.toString();
    }

    private void elemProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i) {
        try {
            writer.append(tabCount)
                    .append(serializer.toStrategyStyle(fields[i].getName()))
                    .append(serialize(fields[i].get(obj), new StringBuilder(), new StringBuilder().append(tabCount).append("   ")));
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
        serializer.elemStylePostProcessing(obj, writer, tabCount, fields, i);
    }

    public String serialize(Object o) {
        StringBuilder writer = new StringBuilder();
        try {
            writer.append(serializer.toStrategyStartingStyle())
                    .append(serializer.toStrategyStyle(o.getClass().getSimpleName()))
                    .append(serialize(o, new StringBuilder(), new StringBuilder().append("   ")))
                    .append(serializer.toStrategyStyle(o.getClass().getSimpleName()))
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private String printArray(Object o, StringBuilder tabCount) throws IOException {
        StringBuilder writer = new StringBuilder();
        serializer.printArrayPreProcessing(tabCount, writer);

        int length = Array.getLength(o);
        for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(o, i);
            try {
                writer.append(tabCount)
                        .append(serializer.toStrategyStyle("element"))
                        .append(serialize(arrayElement, new StringBuilder(), tabCount))
                        .append(serializer.toStrategyStyle("element"));

                serializer.printLastArrayElement(writer, length, i);
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        serializer.printArrayPostProcessing(tabCount, writer);
        return writer.toString();
    }

    private String printCollection(Collection o, StringBuilder tabCount) throws IOException {
        StringBuilder writer = new StringBuilder();
        serializer.printArrayPreProcessing(tabCount, writer);
        for (Object o1 : o) {
            try {
                writer.append(tabCount)
                        .append(serializer.toStrategyStyle("element"))
                        .append(serialize(o1, new StringBuilder(), tabCount))
                        .append(serializer.toStrategyStyle("element"))
                        .append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        serializer.printArrayPostProcessing(tabCount, writer);
        return writer.toString();
    }

    private boolean arrayProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (isArray(obj)) {
            writer.append(printArray(obj, tabCount));
            return true;
        }
        return false;
    }

    private boolean collectonProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (obj instanceof Collection) {
            writer.append(printCollection((Collection) obj, tabCount));
            return true;
        }
        return false;
    }

    private boolean wrapperProcessing(Object obj, StringBuilder writer) {
        if (isWrapper(obj)) {
            serializer.wrapperStrategyProcessing(obj, writer);
            return true;
        }
        return false;
    }

    private boolean nullProcessing(Object obj, StringBuilder writer) {
        if (obj == null) {
            serializer.nullStrategyProcessing(writer);
            return true;
        }
        return false;
    }

}