package Lesson8;

public class Main {

    public static void main(String[] args) {
        MyChainingHashMap<Character, Integer> hashMap = new MyChainingHashMap<>();
        hashMap.put('a', 1);
        hashMap.put('p', 1);
        hashMap.put('t', 1);
        hashMap.put('d', 5);
        hashMap.put('m', 1);
        hashMap.put('h', 1);
        hashMap.put('x', 1);
        hashMap.put('b', 1);
        System.out.println(hashMap.get('d'));
    }

}
