import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public SinglyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    private boolean addFirst(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (head == null) {
            head = new Node<>(element, null);
            tail = head;
        } else {
            head = new Node<>(element, head);
        }

        size += 1;

        return true;
    }

    public boolean addLast(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (head == null) {
            head = new Node<>(element, null);
            tail = head;
        } else {
            tail.next = new Node<>(element, null);
            tail = tail.next;
        }

        size += 1;

        return true;
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return head.data;
    }

    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return tail.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            E data = head.data;
            head = null;
            tail = null;

            size -= 1;

            return data;
        }

        E data = head.data;
        head = head.next;

        size -= 1;

        return data;
    }

    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            E data = head.data;
            head = null;
            tail = null;

            size -= 1;

            return data;
        }

        Node<E> current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        E data = current.next.data;
        current.next = null;

        tail = current;

        size -= 1;

        return data;
    }

    public int size() {
        return size;
    }


    private static class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
