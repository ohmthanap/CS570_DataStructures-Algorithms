package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayList implements a dynamic array.
 *
 * @param <E> is the type of elements that the list will hold.
 */
public class ArrayList<E> implements Iterable<E> {
    private int size;
    private int capacity;
    private E[] array;

    /**
     * Constructs a new ArrayList.
     */
    public ArrayList() {
        size = 0;
        capacity = 1;
        array = (E[]) new Object[capacity];
    }

    /**
     * Adds the given element to the end of the list.
     *
     * @param element the element to be added.
     * @return true if the element is added successfully
     */
    public boolean add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null elements to the list!");
        }

        if (size == capacity) {
            resizeArray(capacity * 2);
        }

        array[size] = element;
        size += 1;

        return true;
    }

    /**
     * Adds the given element at the specified index in the list.
     *
     * @param index   the index in the list where the element is to be added.
     * @param element the element to be added.
     */
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new IllegalArgumentException("Cannot add null elements to the list!");
        }

        if (size == capacity) {
            resizeArray(capacity * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        array[index] = element;
        size += 1;
    }

    /**
     * Clear the entire list.
     */
    public void clear() {
        size = 0;
        capacity = 1;
        array = (E[]) new Object[capacity];
    }

    /**
     * Checks if the given element exists within the list.
     *
     * @param element the element to be checked.
     * @return true if the element exists within the list, false otherwise.
     */
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("The element to be checked cannot be null!");
        }

        for (E e : array) {
            if (e.equals(element)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves the element at the specified index from the list.
     *
     * @param index the index from which to retrieve the element.
     * @return the element at the specified index.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    /**
     * Retrieves the index of the given element if it exists in the list.
     *
     * @param element the element whose index is to be retrieved.
     * @return the index of the given element if it exists in the list, -1 otherwise.
     */
    public int indexOf(E element) {
        if (element == null) {
            throw new IllegalArgumentException("The element to be checked cannot be null!");
        }

        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Check if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return an iterator for the list.
     *
     * @return an iterator for the list.
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Removes the element at the specified index from the list.
     *
     * @param index the index in the list at which the element is to be removed.
     * @return the element currently at the specified index before removal.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E data = array[index];

        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }

        array[size - 1] = null;
        size -= 1;

        return data;
    }

    /**
     * Removes the specified element from the list.
     *
     * @param element the element to be removed.
     * @return true if the element is removed successfully, false otherwise.
     */
    public boolean remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("The element to be removed cannot be null!");
        }

        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Sets the value of the given index in the list to the specified element.
     *
     * @param index   the index at which the element is to be set.
     * @param element the element to be set at the specified index.
     * @return the element currently at the specified index.
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new IllegalArgumentException("The element to be set cannot be null!");
        }

        E data = array[index];
        array[index] = element;

        return data;
    }

    /**
     * Get the current size of the list.
     *
     * @return the current size of the list.
     */
    public int size() {
        return size;
    }
    
    public String toString() {
    	if (size == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size - 1; i++) {
            builder.append(array[i]).append(", ");
        }
        builder.append(array[size - 1]).append("]");

        return builder.toString();
    }

    private void resizeArray(int newCapacity) {
        E[] newArray = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
        capacity = newCapacity;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int current;

        private ArrayListIterator() {
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public E next() {
            if (current >= size) {
                throw new NoSuchElementException();
            }

            E data = array[current];
            current += 1;

            return data;
        }
    }
    
    public static void main(String[] args) {
    	ArrayList<String> arrayList = new ArrayList<>();
    	
    	arrayList.add("Apple");
    	arrayList.add("Banana");
    	arrayList.add("Carrot");
    	arrayList.add("Dragonfruit");
    	arrayList.add("Eggplant");
    	
    	System.out.println(arrayList);
    	System.out.println();

    	System.out.println(arrayList.get(0));
    	System.out.println(arrayList.get(2));
    	System.out.println(arrayList.get(arrayList.size - 1));
    	System.out.println();
    	
    	arrayList.remove(0);
    	System.out.println(arrayList);
    	System.out.println(arrayList.get(0));
    	System.out.println();
    	
    	System.out.println(arrayList);
    	
    	arrayList.remove(arrayList.size - 1);
    	System.out.println(arrayList.get(arrayList.size - 1));
    	System.out.println();
    	
    	System.out.println(arrayList);
    }
}
