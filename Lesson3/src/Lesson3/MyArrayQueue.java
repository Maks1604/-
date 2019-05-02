package Lesson3;

import java.util.NoSuchElementException;

public class MyArrayQueue<T> {
    private Object[] queue = new Object[1];
    private int size = 0;
    private int start = 0;
    private int end = 0;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    private void resize(int capacity) {
        Object[] tmp = new Object[capacity];
        for (int i = 0; i < size; i++) {
        tmp[i] = queue[(start + i) % queue.length];
    }
        queue = tmp;
        start = 0;
        end = size;
    }

    public void enqueue(T t) {
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[end++] = t;
        end %= queue.length;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T t = (T) queue[start];
        queue[start] = null;
        size--;
        start = (start + 1) % queue.length;
        if (size == queue.length / 4 && size > 0) {
            resize(queue.length / 2);
        }
        return t;
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return (T) queue[start];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(queue[(start + i) % queue.length] + ", ");
        }
        return s.toString();
    }
}
