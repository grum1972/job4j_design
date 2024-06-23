package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Balancer {
    public static void split1(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {

        int index = 0;
        while (source.hasNext() && index < nodes.size()) {
            nodes.get(index).add(source.next());
            index++;
            if (index == nodes.size()) {
                index = 0;
            }
        }

    }

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        CyclicIterator<ArrayList<Integer>> cyclicIterator = new CyclicIterator<>(nodes);
        while (cyclicIterator.hasNext() && source.hasNext()) {
            List<Integer> list = cyclicIterator.next();
            list.add(source.next());
        }
    }
}