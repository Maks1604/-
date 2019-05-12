package Lesson4;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {

        Node cursor = first;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T t = cursor.t;
            cursor = cursor.next;
            return t;
        }
    }

    private class Node {
        T t;
        Node next;
        Node previous;

        public Node(Node previous, T t, Node next) {
            this.previous = previous;
            this.t = t;
            this.next = next;
        }
    }

    private int size;
    private Node first = null;
    private Node last = null;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return first.t;
    }

    public void addFirst(T t) {
        Node oldFirst = first;
        first = new Node(null,t, oldFirst);
        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.previous = first;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        Node second = first.next;
        T t = first.t;
        first.next = null;
        first = second;
        size--;
        if (isEmpty()) {
            last = null;
        }
        else {
            second.previous = null;
        }
        return t;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return last.t;
    }

    public void addLast(T t) {
        Node oldLast = last;
        last = new Node(oldLast, t, null);
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        size++;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T item = last.t;
        Node previous = last.previous;
        last.previous = null;
        last = previous;
        size--;
        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (index<=size/2) {
            Node currentNode = first;
            int currentIndex = 0;
            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }
            return currentNode.t;
        } else {
            Node currentNode = last;
            int currentIndex = size-1;
            while (currentIndex > index) {
                currentNode = currentNode.previous;
                currentIndex--;
            }
            return currentNode.t;
        }

    }

    public void set(int index, T t) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (index<=size/2) {
            Node currentNode = first;
            int currentIndex = 0;
            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }
            currentNode.t = t;
        } else {
            Node currentNode = last;
            int currentIndex = size-1;
            while (currentIndex > index) {
                currentNode = currentNode.previous;
                currentIndex--;
            }
            currentNode.t = t;
        }
    }

    public int indexOf(T t) {
        Node currentNode = first;
        int currentIndex = 0;
        while (!currentNode.t.equals(t) && currentNode != null) {
            currentNode = currentNode.next;
            currentIndex++;
        }
        return currentNode != null ? currentIndex : -1;
    }

    public boolean contains(T t) { return indexOf(t) > -1; }

    public T remove(T t) {
        Node currentNode = first;
        while (currentNode != null || !currentNode.t.equals(t)) {
            currentNode = currentNode.next;
        }

        if (currentNode == null) {
            return null;
        }

        if (currentNode == first) {
            return removeFirst();
        }

        if (currentNode == last) {
            return removeLast();
        }

        Node next = currentNode.next;
        Node previous = currentNode.previous;
        previous.next = next;
        next.previous = previous;
        size--;
        currentNode.previous = null;
        currentNode.next = null;
        return currentNode.t;
    }

    public void add(int index, T t) { //addBefore
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(t);
        }
        else if (index == size) {
            addLast(t);
        }
        else {
            Node currentNode = first;
            int currentIndex = 0;
            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }
            Node previous = currentNode.previous;
            Node newNode = new Node(previous, t, currentNode);
            previous.next = newNode;
            currentNode.previous = newNode;
            size++;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node currentNode = first;
        while (currentNode != null) {
            s.append(currentNode.t.toString());
            s.append(", ");
            currentNode = currentNode.next;
        }
        return s.toString();
    }


}