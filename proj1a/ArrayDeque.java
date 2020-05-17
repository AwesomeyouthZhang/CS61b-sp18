import javax.swing.*;
import java.net.PortUnreachableException;

public class ArrayDeque <T> {
    public int nextFirst;
    public int nextLast;
    private int size;
    public T[] items;

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
        if (nextFirst < 0 || nextFirst == items.length) {
            nextFirst = items.length - 1;
        }

        if (nextLast == nextFirst) {
            return true;
        }
        return false;
    }

    public void resize() {
        if (resizeHelper()) {
            T[] temp = (T[]) new Object[size * 2];
            int n = items.length;
            int backOfNextFirst = n - nextFirst;
            System.arraycopy(items, nextFirst, temp, 0, backOfNextFirst);
            System.arraycopy(items, 0, temp, backOfNextFirst - 1, nextFirst);
            items = temp;
            nextFirst = items.length;
            nextLast = n - 1;

        }
    }

    /*Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {

        items[nextFirst] = item;

        size += 1;
        resize();

        nextFirst -= 1;

    }

    public void addLast(T item) {
        size += 1;
        resize();
        items[nextLast] = item;


        nextLast += 1;


    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /*Returns the number of items in the deque.*/
    public int size() {
        return size();

    }

    /*
    Removes and returns the item at the front of the deque.
    If no such item exists, returns null.
    */
    public T removeFirst() {
        if (items[nextFirst + 1] == null) {
            return null;
        }
        nextFirst += 1;
        size -= 1;
        return items[nextFirst + 1];

    }

    public T removeLast() {
        if (items[nextLast - 1] == null) {
            return null;
        }
        nextLast -= 1;
        size -= 1;
        return items[nextLast - 1];
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[index];
    }

    public void printDeque() {

        int temp = nextFirst - 1;
        for (int i = 0; i < items.length + 2; i++) {
            if (items[temp] != null) {
                System.out.print(items[temp] + " ");


            }
            temp++;

            if (temp > items.length - 1) {
                temp = 0;

            }
        }

    }
}


