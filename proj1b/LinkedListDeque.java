

public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private IntNode sentinel;

    private class IntNode {
        private T item;
        private IntNode prev;
        private IntNode next;

        IntNode(T i, IntNode P, IntNode N) {
            item = i;
            next = N;
            prev = P;

        }
    }

    /*
     * Creates an empty deque
     */
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        /*
         * The sequence is crucial.
         * Otherwise will failed by NullPointerException
         * */
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }

    /*
     * Adds a x of type T to the front of the deque
     */
    @Override
    public void addFirst(T x) {
        IntNode temp = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;

    }

    /*
     * Adds a x of type T to the back of the deque
     */
    @Override
    public void addLast(T x) {
        IntNode temp = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    /*
    return true if the deque is empty, false otherwise
    * */
    @Override
    public boolean isEmpty() {
        if (sentinel.prev == sentinel) {
            return true;
        }


        return false;

    }

    /*
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {

        return size;


    }

    /*
     * Prints the items in the deque from first to last.
     * separated by a space.
     */
    @Override
    public void printDeque() {
        IntNode temp = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.item + " ");
        }

    }

    /*
     * Removes and return the items at the front of the deque.
     * If no such items, return null
     */
    @Override
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

    /*
     * Removes and return the items at the back of the deque.
     * If no such items, return null
     */
    @Override
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

    /*Gets the item at the given index, where 0 is the front,
     1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!*/
    @Override
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

    /*Same as get, but uses recursion.*/
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
