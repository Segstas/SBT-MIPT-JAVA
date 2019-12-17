package week9.homework;

import java.lang.reflect.Field;

public class XMLSerializer implements Serializer {


    public void elemStylePostProcessing(Object obj, StringBuilder writer, StringBuilder tabCount, Field[] fields, int i) {
        try {
            if (!isWrapper(fields[i].get(obj))) writer.append(tabCount);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        writer.append(toStrategyStyle(fields[i].getName()))
                .append("\n");
    }

    boolean isWrapper(Object obj) {
        return wrappers.contains(obj);
    }

    public String elemStyleProcessing(String element) {
        StringBuilder writer = new StringBuilder();
        return writer.append("<")
                .append(element)
                .append(">").toString();
    }

    @Override
    public void elemPostprocessing(StringBuilder writer, StringBuilder tabCount) {

    }

    @Override
    public void elemPreprocessing(StringBuilder writer, StringBuilder tabCount) {
        writer.append("\n");
    }


    public void nullStrategyProcessing(StringBuilder writer) {
        writer.append("null");
    }


    public void wrapperStrategyProcessing(Object obj, StringBuilder writer) {
        writer.append(obj);
    }


    public void printLastArrayElement(StringBuilder writer, int length, int i) {
    }

    public void printArrayPreProcessing(StringBuilder tabCount, StringBuilder writer) {
        writer.append(tabCount).append("    ").append("\n");
    }


    public void printArrayPostProcessing(StringBuilder tabCount, StringBuilder writer) {
        writer.append(tabCount).append("\n");
    }


    public String toStrategyStyle(String element) {
        StringBuilder writer = new StringBuilder();
        return writer.append("<")
                .append(element)
                .append(">")
                .toString();
    }

    public String toStrategyStartingStyle() {
        return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
    }

}
