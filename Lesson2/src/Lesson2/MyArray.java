package Lesson2;

import java.util.Comparator;
import java.util.Iterator;

public class MyArray<T> implements Iterable<T> {
    private int size = 0;
    private Object[] array = new Object[1];

    public MyArray(int size) {
        this.array = new Object[size];
        this.size = size;
    }

    public MyArray(MyArray array) {
        this(array.size);
        for (int i = 0; i < array.size; i++) {
            this.array[i] = array.getItemArray(i);
        }
    }

    public T getItemArray(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void setItemArray(int index, T t) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = t;
    }

    private void resize(int length) {
        Object[] temp = new Object[length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void add(T t) {
        if (size == array.length) {
            resize(2 * array.length);
        }
        array[size] = t;
        size++;
    }

    public boolean remove(T t) {
        int index = indexOf(t);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        if (size == array.length / 4 && size > 0) {
            resize(array.length / 2);
        }
        return true;
    }

    public int indexOf(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(array)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isExist(T t) {
        return indexOf(t) != -1;
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            T item = (T) array[cursor];
            cursor++;
            return item;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(array[i] + ", ");
        }
        return s.toString();
    }

    public int size() {
        return size;
    }

    private void exch(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean less(T t1, T t2, Comparator<T> cmp) {
        return cmp.compare(t1, t2) < 0;
    }

    public void selectionSort(Comparator<T> cmp) {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less((T) array[j], (T) array[min], cmp)) {
                    min = j;
                }
            }
            exch(i, min);
        }
    }

    public void insertionSort(Comparator<T> cmp) {
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less((T) array[j], (T) array[j - 1], cmp)) {
                    exch(j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public boolean binarySearch(T t, Comparator<T> cmp) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (cmp.compare(t, (T) array[mid]) < 0) {
                high = mid - 1;
            } else if (cmp.compare(t, (T) array[mid]) > 0) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
