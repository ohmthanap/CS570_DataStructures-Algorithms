import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack<E> {
    private LinkedList<E> linkedList;

    public Stack() {
        linkedList = new LinkedList<>();
    }

    public void push(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        linkedList.addFirst(element);
    }

    public E peek() {
        if (linkedList.isEmpty()) {
            throw new EmptyStackException();
        }

        return linkedList.getFirst();
    }

    public E pop() {
        if (linkedList.isEmpty()) {
            throw new EmptyStackException();
        }

        return linkedList.removeFirst();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public int size() {
        return linkedList.size();
    }
}
