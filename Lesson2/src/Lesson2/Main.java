package Lesson2;

import java.util.Random;


public class Main {
    static Random random = new Random();


    public static void main(String[] args) {
        int n = 1000000;
        int a = 100;
        int b = 10000;

        MyArray array = CreateRandomArray(n, a, b);
        System.out.print(array.toString());
        System.out.println();
        array.insertionSort(new IntegerComparator());
        System.out.print(array.toString());


    }

    private static MyArray<Integer> CreateRandomArray(int n, int a, int b) {
        MyArray<Integer> array = new MyArray<>(n);
        for (int i = 0; i < n; i++) {
            array.setItemArray(i, random.nextInt(b - a) + a);
        }
        return array;
    }


}

