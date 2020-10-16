import java.util.*;

public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Key> iterator() {

        return new BSTIterator( root);
    }

    public class BSTIterator implements Iterator<Key> {

        private List<Key> inOrderList;
        private Iterator<Key> it;

        public BSTIterator(Node root) {
            inOrderList = new ArrayList<>();
            inOrder(root);

            it = this.inOrderList.iterator();

        }

        private void inOrder(Node x) {
            if (x != null) {
                inOrder(x.left);
                inOrderList.add(x.key);
                inOrder(x.right);
            }


        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {

            return this.it.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Key next() {
            return this.it.next();
        }
    }


    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        public Node() {
            root = null;
            size = 0;

        }

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }


    }

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return null;
        }
        int com = key.compareTo(x.key);
        if (com < 0) {
            return get(x.left, key);
        } else if (com > 0) {
            return get(x.right, key);

        } else {
            return x.value;
        }
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, value);

    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);

        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;

    }



}


