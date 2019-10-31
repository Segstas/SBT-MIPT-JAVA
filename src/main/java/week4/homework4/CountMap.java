package week4.homework4;

import java.util.Map;

public interface CountMap<K> {

    void add(K smth);

    int getCount(K smth);

    int remove(K smth);

    int size();

    void addAll(CountMap<? extends K> map);

    Map<K, Integer> toMap();

    void toMap(Map<? super K, Integer> destination);
}

