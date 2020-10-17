import java.lang.reflect.Array;
import java.net.HttpRetryException;
import java.util.*;
import java.util.function.Consumer;

public class BSTMap<Key extends Comparable,Value> implements Map61B<Key,Value> {
    BST bst= new BST();


    @Override
    public void clear() {
        bst = new BST<>();

    }

    @Override
    public boolean containsKey(Key key) {
        return bst.contains(key);
    }

    @Override
    public Value get(Key key) {
        return (Value) bst.get(key);
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public void put(Key key, Value value) {
        bst.put(key,value);

    }



    @Override
    public Set<Key> keySet() {
        Set<Key> keySet= new TreeSet<>();
        for (Object key:bst) {
            keySet.add((Key) key);
        }
        return keySet;
    }

    @Override
    public Value remove(Key key) {
        return (Value) bst.remove(key);
    }

    @Override
    public Value remove(Key key, Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Key> iterator() {

        return bst.iterator();
        };
    }


