package week4.homework4;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<K> implements CountMap<K> {

    private HashMap<K, Integer> map = new HashMap<>();

    public void add(K key) {
        if (map.containsKey(key)) {
            map.put(key, 1);
        } else {
            map.put(key, map.get(key) + 1);
        }
    }

    public int getCount(K key) {
        return map.containsKey(key) ? (map.get(key)) : 0;
    }

    public int remove(K key) {
        int count = getCount(key);
        map.remove(key);
        return count;
    }

    public int size() {
        return map.size();
    }

    public void addAll(CountMap<? extends K> source) {
        for (Map.Entry<? extends K, Integer> entry : source.toMap().entrySet()) {
            Integer sourceCount = (entry.getValue());
            if (this.map.containsKey(entry.getKey())) {
                this.map.put(entry.getKey(), sourceCount + getCount(entry.getKey()));
            } else {
                this.map.put(entry.getKey(), sourceCount);
            }
        }
    }

    public Map<K, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<? super K, Integer> destination) {
        for (K key : map.keySet()) {
            destination.put(key, map.get(key));
        }
    }
}