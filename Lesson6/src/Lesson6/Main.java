package Lesson5;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        int countNotBalanced = 0;
        MyTreeMap<Integer, Integer> myTreeMap[] = new MyTreeMap[20];
        for (int i = 0; i < myTreeMap.length; i++) {
            myTreeMap[i] = new MyTreeMap<>();
            do {
                myTreeMap[i].put(random.nextInt(200)-100,1);
            }while (myTreeMap[i].height()<6);

            if (!myTreeMap[i].isBalanced()) {
                countNotBalanced++;
            }
        }

        System.out.println(countNotBalanced/myTreeMap.length*100+"%");


    }
}
