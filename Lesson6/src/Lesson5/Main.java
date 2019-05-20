package Lesson5;

public class Main {

    public static void main(String[] args) {
        MyTreeMap<Character, Integer> myTreeMap = new MyTreeMap<>();
        myTreeMap.put('S', 1);
        myTreeMap.put('E', 1);
        myTreeMap.put('A', 1);
        myTreeMap.put('R', 1);
        myTreeMap.put('C', 1);
        myTreeMap.put('H', 1);
        myTreeMap.put('X', 1);
        myTreeMap.remove('E');
    }
}
