package week4.homework4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayLit() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static <T> void add(List<? super T> destination, T o) {
        destination.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<T> c2) {
        removeFrom.removeAll(c2);
    }


    public static <T> boolean containsAll(List<T> c1, List<? extends T> c2) {
        for (T elem : c2) {
            if (!c1.contains(elem)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean containsAny(List<T> c1, List<? extends T> c2) {
        for (T elem : c2) {
            if (c1.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<T>> List range(List<? extends T> list, T min, T max) {
        List<? super T> destination = new ArrayList<>();
        for (T elem : list) {
            if (elem.compareTo(min) > 0 && elem.compareTo(max) < 0) {
                destination.add(elem);
            }
        }
        return destination;
    }

    public static <T> List<? super T> range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        List<? super T> destination = new ArrayList<>();
        for (T elem : list) {
            if (comparator.compare(elem, min) > 0 && comparator.compare(elem, max) < 0) {
                destination.add(elem);
            }
        }
        return destination;
    }
}