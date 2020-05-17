
/*

public class LinkedListDeque<T> {
    private int size;
    private IntNode sentinel;

    private class IntNode {
        public T item;
        public IntNode prev;
        public IntNode next;

        IntNode(T i, IntNode P, IntNode N) {
            item = i;
            next = N;
            prev = P;

        }
    }

    */
/*
 * Creates an empty deque
 *//*

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        */
/*
 * The sequence is crucial.
 * Otherwise will failed by NullPointerException
 * *//*

        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }

    */
/*
 * Adds a x of type T to the front of the deque
 *//*

    public void addFirst(T x) {
        IntNode temp = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;

    }

    */
/*
 * Adds a x of type T to the back of the deque
 *//*

    public void addLast(T x) {
        IntNode temp = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    */
/*
    return true if the deque is empty, false otherwise
    * *//*

    public boolean isEmpty() {
        if (sentinel.prev == sentinel) {
            return true;
        }


        return false;

    }

    */
/*
 * Returns the number of items in the deque.
 *//*

    public int size() {

        return size;


    }

    */
/*
 * Prints the items in the deque from first to last.
 * separated by a space.
 *//*

    public void printDeque() {
        IntNode temp = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.item + " ");
        }

    }

    */
/*
 * Removes and return the items at the front of the deque.
 * If no such items, return null
 *//*

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removedItem;
    }

    */
/*
 * Removes and return the items at the back of the deque.
 * If no such items, return null
 *//*

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removedItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removedItem;

    }

    */
/*Gets the item at the given index, where 0 is the front,
     1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!*//*

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        IntNode temp = sentinel.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    */
/*Same as get, but uses recursion.*//*

    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }


        return getRecursivehelper(sentinel.next, index);
    }

    private T getRecursivehelper(IntNode temp, int index) {


        if (index == 0) {

            return temp.item;

        }
        temp = temp.next;
        return getRecursivehelper(temp, index - 1);
    }


}
*/
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

    /**
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