package Lesson3;

import java.util.NoSuchElementException;

public class MyArrayDek<T> {
    private Object[] stack = new Object[1];
    private int sizeRight = 0;
    private int sizeLeft = 0;
    private int size = 0;

    public int getSizeRight() {
        return sizeRight;
    }

    public int getSizeLeft() {
        return sizeLeft;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    private void resize(int capacity) {
        Object[] tmp = new Object[capacity];
        int newSizeLeft = sizeLeft;
        sizeLeft = (capacity - size) / 2;
        sizeRight = size + sizeLeft;
        for (int i = sizeLeft, j=newSizeLeft; i < sizeRight; i++, j++) {
            tmp[i] = stack[j];
        }
        stack = tmp;

    }

    public void insertLeft(T t) {
        if (sizeLeft<0) {
            resize(stack.length * 2);
        }

        stack[sizeLeft--] = t;
        size++;
    }

    public void insertRight(T t) {
        if (sizeRight == stack.length) {
            resize(stack.length * 2);
        }

        stack[sizeRight++] = t;
        size++;
    }

    public T removeLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T t = (T) stack[sizeLeft + 1];
        stack[sizeLeft + 1] = null;
        sizeLeft++;
        if (size == stack.length / 4 && size > 0) {
            resize(stack.length / 2);
        }
        return t;
    }

    public T removeRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T t = (T) stack[sizeRight - 1];
        stack[sizeRight - 1] = null;
        sizeRight--;
        if (size == stack.length / 4 && size > 0) {
            resize(stack.length / 2);
        }
        return t;
    }

    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return (T) stack[size - 1];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < stack.length; i++) {
            s.append(stack[i] + ", ");
        }
        return s.toString();
    }
}
