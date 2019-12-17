package week9.homework;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

public abstract class SerializerImpl {

    private final Set<Class<?>> wrappers = Wrappers.wrapperSet;

    abstract void elemPreprocessing(StringBuilder writer, StringBuilder tabCount);

    abstract boolean nullProcessing(Object obj, StringBuilder writer);

    abstract boolean collectonProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException;

    abstract boolean arrayProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException;

    abstract boolean wrapperProcessing(Object obj, StringBuilder writer);

    abstract String printArray(Object o, StringBuilder tabCount) throws IOException;

    abstract String printCollection(Collection o, StringBuilder tabCount) throws IOException;

    abstract String serialize(Object o);

    boolean isCollection(Object obj) {
        return obj instanceof Collection;
    }

    boolean isArray(Object obj) {
       return obj.getClass().isArray();
    }

    boolean isWrapper(Object obj) {
        return wrappers.contains(obj);
    }

    abstract void elemProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i);

    abstract void elemPostprocessing(StringBuilder writer, StringBuilder tabCount);




    String serialize(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException {
        if (nullProcessing(obj, writer)) return writer.toString();
        if (wrapperProcessing(obj, writer)) return writer.toString();
        if (arrayProcessing(obj, writer, tabCount)) return writer.toString();
        if (collectonProcessing(obj, writer, tabCount)) return writer.toString();
        Class clazz = obj.getClass();
        elemPreprocessing(writer, tabCount);
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            elemProcessing(obj, writer, tabCount, fields, i);
        }
        elemPostprocessing(writer, tabCount);
        return writer.toString();
    }


}