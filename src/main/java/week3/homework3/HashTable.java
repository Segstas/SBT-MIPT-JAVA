package week3.homework3;

import static java.lang.Math.abs;

class HashTable<K, V> {

    private HashNode<K, V>[] hashNodeArray;
    private int capacity;

    public HashTable() {
        this.capacity = 12;
        this.hashNodeArray = (HashNode<K, V>[]) new HashNode[capacity];
    }

    public int size() {
        int counter = 0;
        for (int i = 0; i < hashNodeArray.length; i++) {
            if (hashNodeArray[i] != null) {
                if (!hashNodeArray[i].isDeleted)
                    counter++;
            }
        }
        return counter;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    ////hash function
    private int getNodeListIndex(K key) {
        int hashCode = key.hashCode();
        return abs(hashCode % capacity);
    }

    private void resize() {
        int oldCapacity = capacity;

        HashNode<K, V>[] newHashNodeArray = new HashNode[oldCapacity];
        for (int i = 0; i < newHashNodeArray.length; i++) {
            newHashNodeArray[i] = hashNodeArray[i];
        }
        this.capacity = this.capacity * 2;
        this.hashNodeArray = (HashNode<K, V>[]) new HashNode[capacity];
        for (int i = 0; i < newHashNodeArray.length; i++) {
            if (newHashNodeArray[i] != null) {
                if (!newHashNodeArray[i].isDeleted) {
                    this.put(newHashNodeArray[i].key, newHashNodeArray[i].value);
                }
            }
        }
    }


    public void put(K key, V value) {
        int nodeIndex = getNodeListIndex(key);
        if (nodeIndex >= capacity - 1) {
            this.resize();
            this.put(key, value);
            return;
        }

        while ((hashNodeArray[nodeIndex] != null && nodeIndex < capacity)) {
            if (hashNodeArray[nodeIndex].isDeleted) break;
            if (hashNodeArray[nodeIndex].key == key) {
                hashNodeArray[nodeIndex].value = value;
                return;
            }
            nodeIndex++;
        }

        if (hashNodeArray[nodeIndex] == null) {
            hashNodeArray[nodeIndex] = new HashNode<>(key, value);
            return;
        } else {
            if (hashNodeArray[nodeIndex].isDeleted) {
                hashNodeArray[nodeIndex].setKey(key);
                hashNodeArray[nodeIndex].setValue(value);
                hashNodeArray[nodeIndex].isDeleted = false;
                return;
            }
        }
    }

    public V get(K searchedKey) {

        int nodeIndex = getNodeListIndex(searchedKey);
        if (hashNodeArray.length <= nodeIndex - 1) {
            return null;
        }
        if (hashNodeArray[nodeIndex] == null) {
            return null;
        }
        for (int i = 0; i < hashNodeArray.length; i++) {
            if (hashNodeArray[nodeIndex] == null) {
                return null;
            }
            if (hashNodeArray[nodeIndex].getKey().equals(searchedKey) && !hashNodeArray[nodeIndex].isDeleted) {
                return hashNodeArray[nodeIndex].getValue();
            }
        }
        return null;
    }


    public void remove(K searchedKey) {
        if (!contains(searchedKey)) return;
        int nodeIndex = getNodeListIndex(searchedKey);
        while (hashNodeArray[nodeIndex] == null) {
            nodeIndex++;
        }
        while (hashNodeArray[nodeIndex].key != searchedKey) {
            nodeIndex++;
            while (hashNodeArray[nodeIndex] == null) nodeIndex++;
        }
        if (hashNodeArray[nodeIndex].key == searchedKey) {
            hashNodeArray[nodeIndex].setKey(null);
            hashNodeArray[nodeIndex].setValue(null);
            hashNodeArray[nodeIndex].isDeleted = true;
            return;
        }
    }


    public boolean contains(K searchedKey) {
        return get(searchedKey) != null;
    }
}