package week9.homework;

import java.lang.reflect.Field;
import java.util.Set;

interface Serializer {
    Set<Class<?>> wrappers = Wrappers.wrapperSet;

    void elemPreprocessing(StringBuilder writer, StringBuilder tabCount);


    void elemPostprocessing(StringBuilder writer, StringBuilder tabCount);

    String toStrategyStyle(String element);

    void printArrayPostProcessing(StringBuilder tabCount, StringBuilder writer);

    void printArrayPreProcessing(StringBuilder tabCount, StringBuilder writer);

    void printLastArrayElement(StringBuilder writer, int length, int i);

    void wrapperStrategyProcessing(Object obj, StringBuilder writer);

    String toStrategyStartingStyle();

    void nullStrategyProcessing(StringBuilder writer);

    String elemStyleProcessing(String element);

    void elemStylePostProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i);
}