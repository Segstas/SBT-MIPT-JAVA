package week9.homework;

import java.lang.reflect.Field;

public class JSONSerializer implements Serializer {

    @Override
    public void elemPostprocessing(StringBuilder writer, StringBuilder tabCount) {
        writer.append(tabCount.toString())
                .append("}");
    }


    public void elemStylePostProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i) {
        if (i < fields.length - 1) writer.append(",");
        writer.append("\n");
    }

    @Override
    public void elemPreprocessing(StringBuilder writer, StringBuilder tabCount) {
        writer.append(tabCount.toString())
                .append("{")
                .append("\n");
    }


    public String elemStyleProcessing(String element) {
        StringBuilder writer = new StringBuilder();
        return writer.append("\"")
                .append(toStrategyStyle(element))
                .append("\"")
                .append(":").toString();
    }


    public void nullStrategyProcessing(StringBuilder writer) {
        writer.append("\"").append("null").append("\"");
    }


    public void wrapperStrategyProcessing(Object obj, StringBuilder writer) {
        writer.append("\"").append(obj).append("\"");
    }

    @Override
    public String toStrategyStartingStyle() {
        return "";
    }


    public void printLastArrayElement(StringBuilder writer, int length, int i) {
        if (i < length - 1) writer.append(",");
    }

    public void printArrayPostProcessing(StringBuilder tabCount, StringBuilder writer) {
        writer.append(tabCount).append("]");
    }

    public void printArrayPreProcessing(StringBuilder tabCount, StringBuilder writer) {
        writer.append(tabCount).append("[").append("\n");
    }


    public String toStrategyStyle(String element) {
        return "";
    }

    public String toStrategyStartingStyle(String element) {
        return "";
    }
}
