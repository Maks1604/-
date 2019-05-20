package Lesson5;

public class Main {

    public static void main(String[] args) {
        MyTreeMap<Integer, Integer> myTreeMap = new MyTreeMap<>();
        myTreeMap.put(25, 1);
        myTreeMap.put(20, 1);
        myTreeMap.put(22, 1);
        myTreeMap.put(10, 1);
        myTreeMap.put(40, 1);
        myTreeMap.put(9, 1);
        myTreeMap.put(45, 1);
        myTreeMap.put(50, 1);

        System.out.println(myTreeMap.height());
        System.out.println(myTreeMap.isBalanced());

    }
}
