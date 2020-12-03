import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<Key, Value> implements Map61B<Key, Value> {
    public static int initialSize = 16;
    private int size;
    public static double loadFactor = 0.75;
    SeparateChainingHashST<Key, Value> separateChainingHashST;

    public MyHashMap() {
        this(initialSize, loadFactor);


    }

    public MyHashMap(int initialSize) {
        this(initialSize, loadFactor);

    }

    public MyHashMap(int initialSize, double loadFactor) {
        separateChainingHashST = new SeparateChainingHashST<>(initialSize);

        MyHashMap.initialSize = initialSize;
        MyHashMap.loadFactor = loadFactor;

    }

    private void resize(int capacity) {
        separateChainingHashST.resize(capacity);
        MyHashMap.initialSize = capacity;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        this.separateChainingHashST = new SeparateChainingHashST<>();
        this.size = 0;

    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(Key key) {

        return separateChainingHashST.get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public Value get(Key key) {

        return separateChainingHashST.get(key);
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value) {
        if (!containsKey(key)) {
            size += 1;
        }
        separateChainingHashST.put(key, value);
        if ((double) size / initialSize > loadFactor) {
            resize(initialSize * 2);

        }

    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<Key> keySet() {
        Set<Key> set = new HashSet<>();
        for (Key key : separateChainingHashST.keys()) {
            set.add(key);
        }
        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public Value remove(Key key) {
        if (!containsKey(key)) {
            return null;
        }
        return separateChainingHashST.delete(key);

    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     *
     * @param key
     * @param value
     */
    @Override
    public Value remove(Key key, Value value) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Key> iterator() {

        return keySet().iterator();
    }
}
