package Lesson4;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Character> linkedList = new MyLinkedList<>();
        linkedList.addFirst('a');
        linkedList.addFirst('b');
        linkedList.addFirst('c');
        System.out.println(linkedList.size());
        System.out.println(linkedList);
        System.out.println(linkedList.get(1));
        linkedList.set(1, 'd');
        System.out.println(linkedList.get(1));
        linkedList.addFirst('e');
        linkedList.addLast('f');
        linkedList.add(2, 'g');
        System.out.println(linkedList);
        linkedList.removeLast();
        linkedList.removeFirst();
        System.out.println(linkedList.size());
        //System.out.println(linkedList);

        for (Character item:
                linkedList) {
            System.out.println(item);
        }
        
    }
}
