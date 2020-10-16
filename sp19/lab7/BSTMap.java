import java.util.Iterator;
import java.util.Set;

public class BSTMap<Key extends Comparable,Value> implements Map61B<Key,Value>{
    BST<Key,Value>  bst= new BST();


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
        return bst.get(key);
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

        throw new UnsupportedOperationException();
    }

    @Override
    public Value remove(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value remove(Key key, Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Key> iterator() {
        throw new UnsupportedOperationException();
    }
}
