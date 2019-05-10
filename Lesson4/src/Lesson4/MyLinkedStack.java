package Lesson4;

public class MyLinkedStack<T> {
    private MyLinkedList<T> stack = new MyLinkedList<>();

    public void push(T t) {stack.addFirst(t);}

    public T pop() {return stack.removeFirst();}

    public int size() {return stack.size();}

    public boolean isEmpty() {return stack.isEmpty();}
}
