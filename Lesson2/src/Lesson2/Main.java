package Lesson2;

import java.util.Random;


public class Main {
    static Random random = new Random();


    public static void main(String[] args) {
        int n = 30000;
        int a = 100;
        int b = 10000;
        System.out.println("---------------------------------------------");
        System.out.println(" № | Сортировка втавкой | Сортировка выбором");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < 20 ; i++) {
            MyArray array = CreateRandomArray(n, a, b);
            double insert =insertSort(new MyArray(array));
            double selection = selectionSort(new MyArray(array));
            System.out.printf("%4d|      %5.3f         |        %5.3f       \n", i+1,insert,selection);
            System.out.println("---------------------------------------------");
        }




    }


    private static double selectionSort (MyArray array){
        double startTime =  System.currentTimeMillis()/1000.0;
        array.selectionSort(new IntegerComparator());
        double stopTime = System.currentTimeMillis()/1000.0-startTime;
        return stopTime;
    }

    private static double insertSort (MyArray array){
        double startTime = System.currentTimeMillis()/1000.0;
        array.insertionSort(new IntegerComparator());
        double stopTime = System.currentTimeMillis()/1000.0-startTime;
        return stopTime;
    }

    private static MyArray<Integer> CreateRandomArray(int n, int a, int b) {
        MyArray<Integer> array = new MyArray<>(n);
        for (int i = 0; i < n; i++) {
            array.setItemArray(i, random.nextInt(b - a) + a);
        }
        return array;
    }


}

