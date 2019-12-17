package week9.homework;

import java.util.HashSet;
import java.util.Set;

class Wrappers {
    static Set<Class<?>> wrapperSet = getWrappers();

    private static Set<Class<?>> getWrappers() {
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
}
