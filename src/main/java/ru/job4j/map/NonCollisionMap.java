package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean rsl = (table[index] == null);
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }

        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash(hash) & (capacity - 1);
    }

    private void expand() {

        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity *= 2;
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int index = getIndex(entry.key);
                newTable[index] = entry;
            }
        }
        table = newTable;

    }

    private boolean checkKey(int index, K key) {
        return Objects.hashCode(table[index].key) == Objects.hashCode(key)
                && Objects.equals(table[index].key, key);
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = getIndex(key);
        if (table[index] != null) {
            if (checkKey(index, key)) {
                value = table[index].value;
            }
        }
        return value;
    }

    private int getIndex(K key) {
        int index = indexFor(Objects.hashCode(key));
        Objects.checkIndex(index, capacity);
        return index;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = getIndex(key);
        if (table[index] != null) {
            if (checkKey(index, key)) {
                table[index] = null;
                modCount--;
                count--;
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
    }
}