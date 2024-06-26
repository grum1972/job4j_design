package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> tempNode = head;
            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }
            tempNode.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        size--;
        Node<T> tempNode = head;
        head = tempNode.next;
        tempNode.next = null;
        return tempNode.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(index++);
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}