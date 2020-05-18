
/*
public class ArrayDeque<T> {
    public int nextFirst;
    public int nextLast;
    private int size;
    public T[] items;gur

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;

    }

    private Boolean resizeHelper() {
        if (nextLast > items.length) {
            nextLast = 0;
        }
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }

        if (size == items.length) {
            return true;
        }
        return false;
    }

    public void resize() {
        if (resizeHelper()) {
            T[] temp = (T[]) new Object[size * 2];
            int n = items.length - 1;
            int backOfNextFirst = items.length - nextFirst;
            System.arraycopy(items, nextFirst + 1, temp, 0, backOfNextFirst - 1);
            System.arraycopy(items, 0, temp, backOfNextFirst - 1, nextFirst + 1);
            items = temp;
            nextFirst = items.length - 1;
            nextLast = n + 1;

        }
    }

    *//*Adds an item of type T to the front of the deque.*//*
    public void addFirst(T item) {
        resize();

        items[nextFirst] = item;


        size += 1;

        nextFirst -= 1;


    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;


        size += 1;
        nextLast += 1;


    }

    *//*Returns true if deque is empty, false otherwise.*//*
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    *//*Returns the number of items in the deque.*//*

    public int size() {
        return size();

    }

    *//*
    Removes and returns the item at the front of the deque.
    If no such item exists, returns null.
    *//*
    public T removeFirst() {
        T temp = items[nextFirst];
        nextFirst += 1;
        if (nextFirst > items.length) {
            nextFirst = 0;
        }

        size -= 1;
        items[nextFirst] = null;
        return temp;

    }

    public T removeLast() {
        T temp = items[nextLast];

        nextLast -= 1;
        if (nextLast < 0) {
            nextFirst = items.length - 1;
        }
        items[nextLast] = null;
        size -= 1;
        return items[nextLast - 1];
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        index = (nextFirst + 1 + index) % items.length;

        return items[index];
    }

    public void printDeque() {

        int temp = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[temp] + " ");
            temp++;
            temp = temp % items.length;


        }


    }

*//*    public static void main(String[] args) {
        ArrayDeque test = new ArrayDeque();
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addLast(10);
        test.addLast(14);
        test.addLast(18);
        test.addLast(17);
        test.addLast(16);
        test.addLast(15);
        test.addLast(14);
        //test.removeFirst();
        test.removeLast();
        test.addFirst(18);
  System.out.println( test.get(2));
  test.printDeque();
  test.addLast(100);


    }*//*
}*/



public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;

    /**
     * Creates an empty array deque.
     * The starting size of your array should be 8.
     */
    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**
     * Creates a deep copy of other.
     */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];

        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[curr];
            curr = plusOne(curr);
        }

        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     * Must take constant time.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int i = plusOne(nextFirst);

        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }

    /*
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int first = plusOne(nextFirst);
        T firstItem = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;

        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }

        return firstItem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int last = minusOne(nextLast);
        T lastItem = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;

        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }

        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque and must take constant time.
     */
    public T get(int index) {
        if (index > size) {
            return null;
        }

        index = (plusOne(nextFirst) + index) % items.length;
        return items[index];
    }
}