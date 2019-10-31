package week6.homework6;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JSONSerializer implements Serializer {


    private final Set<Class<?>> wrappers = getWrappers();

    public void serialize(Object obj, FileWriter writer) throws IOException {
        if (obj == null || isWrapper(obj)) {
            writer.write("\"" + obj + "\"");
            writer.flush();
            return;
        }
        Class clazz = obj.getClass();
        writer.write("{");
        writer.flush();
        Field[] fields = clazz.getDeclaredFields();
        ////  for (Field o : clazz.getDeclaredFields())
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                if (fields[i].get(obj) == null || isWrapper(fields[i].get(obj))) {
                    writer.write("\"" + fields[i].getName() + "\"" + ":" + "\"" + fields[i].get(obj) + "\"");
                    if (i < fields.length - 1) writer.write(",");
                    writer.flush();
                } else {
                    if (isArray(fields[i].get(obj))) {
                        writer.write(fields[i].getName() + ":");
                        writer.flush();
                        printArray(fields[i].get(obj), writer);
                    } else {
                        if (isCollection(fields[i].get(obj))) {
                            writer.write(fields[i].getName() + ":");
                            writer.flush();
                            printCollection((Collection) fields[i].get(obj), writer);
                        } else {
                            writer.write("\"" + fields[i].getName() + "\"" + ":");
                            writer.flush();
                            serialize(fields[i].get(obj), writer);
                            if (i < fields.length - 1) writer.write(",");
                            writer.flush();
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        writer.write("}");
        writer.flush();
    }

    private Set<Class<?>> getWrappers() {
        Set<Class<?>> wrapperSet = new HashSet<>();
        wrapperSet.add(Void.class);
        wrapperSet.add(Boolean.class);
        wrapperSet.add(Integer.class);
        wrapperSet.add(Byte.class);
        wrapperSet.add(Short.class);
        wrapperSet.add(Long.class);
        wrapperSet.add(Float.class);
        wrapperSet.add(Double.class);
        wrapperSet.add(String.class);
        wrapperSet.add(Character.class);
        return wrapperSet;
    }


    private boolean isWrapper(Object o) {
        return wrappers.contains(o.getClass());
    }

    private boolean isCollection(Object o) {
        return o instanceof Collection;
    }

    private boolean isArray(Object o) {
        return o.getClass().isArray();
    }

    private void printArray(Object o, FileWriter writer) throws IOException {
        writer.write("[");
        writer.flush();
        if (o.getClass().isArray()) {
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object arrayElement = Array.get(o, i);
                try {
                    serialize(arrayElement, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        writer.write("]");
        writer.flush();
    }

    private void printCollection(Collection o, FileWriter writer) throws IOException {
        writer.write("[");
        for (Object o1 : o) {
            try {
                serialize(o1, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.write("]");
    }

}
