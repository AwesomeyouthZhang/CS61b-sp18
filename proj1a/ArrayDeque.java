
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
    private int nextFirst;
    private int nextLast;
    private int size;
    private T[] items;
    private static final int INIT_CAPACITY = 8;
    private static final int FACTOR = 2;


    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextLast = 2;
        nextFirst = 1;
    }

    private int minusOne(int index) {
        index = (index - 1 + items.length) % items.length;
        return index;
    }

    private int plusOne(int index) {
        index = (index + 1 + items.length) % items.length;
        return index;
    }

    private void resize(int newcapacity) {
        T[] temArray = (T[]) new Object[newcapacity];
        int n = (size - plusOne(nextFirst));
        System.arraycopy(items,
                plusOne(nextFirst),
                temArray,
                0,
                n);
        System.arraycopy(items,
                0,
                temArray,
                n,
                plusOne(nextFirst));
        items = temArray;
        nextFirst = newcapacity - 1;
        nextLast = size;


    }


    public void addFirst(T index) {
        if (size == items.length) {
            resize(size * FACTOR);
        }
        items[nextFirst] = index;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T index) {
        if (size == items.length) {
            resize(size * FACTOR);
        }
        items[nextLast] = index;
        size += 1;
        nextLast = plusOne(nextLast);

    }

    public T removeFirst() {
        T removed = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (items.length > 8 && size < items.length * 0.25) {
            resize(items.length / 2);
        }
        return removed;

    }

    public T removeLast() {
        T removed = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (items.length > 8 && size < items.length * 0.25) {
            resize(items.length / 2);
        }
        return removed;
    }

    public void printDeque() {
        int temp = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[temp] + " ");
            temp = plusOne(temp);
        }
    }

    public boolean isEmpty() {
        return size == 0;

    }

    public int size() {
        if (size < 0) {
            return 0;
        }
        return size;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[plusOne(nextFirst + index)];
    }



/*
    public static void main(String[] args) {
        ArrayDeque test = new ArrayDeque();

        test.addFirst(10);
        test.addFirst(9);
        test.addFirst(8);
        test.addFirst(7);
        test.addFirst(6);
        test.addFirst(5);

        test.addFirst(4);
        test.addFirst(3);
        test.addFirst(2);
        test.addFirst(1);
        test.addFirst(0);

        test.addLast(11);
        test.addLast(12);
        test.addLast(13);
        test.addLast(14);
        test.addLast(15);
        test.addLast(16);
        test.addLast(17);
        test.removeFirst();
        test.removeLast();
        test.addLast(17);
        test.printDeque();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();test.removeLast();




        System.out.print(test.get(1));


    }*/
}




