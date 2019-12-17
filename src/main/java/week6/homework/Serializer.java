package week9.homework;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

interface Serializer {
    Set<Class<?>> wrappers = Wrappers.wrapperSet;

    void elemPreprocessing(StringBuilder writer, StringBuilder tabCount);

    boolean nullProcessing(Object obj, StringBuilder writer);

    boolean collectonProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException;

    boolean arrayProcessing(Object obj, StringBuilder writer, StringBuilder tabCount) throws IOException;

    boolean wrapperProcessing(Object obj, StringBuilder writer);

    String printArray(Object o, StringBuilder tabCount) throws IOException;

    String printCollection(Collection o, StringBuilder tabCount) throws IOException;

    String serialize(Object o);

    boolean isCollection(Object obj);

    boolean isArray(Object obj);

    boolean isWrapper(Object obj);

    void elemProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i);

    void elemPostprocessing(StringBuilder writer, StringBuilder tabCount);
}
