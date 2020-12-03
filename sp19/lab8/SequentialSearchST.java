import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: ZhixianZhang
 * @time: 2020/11/18 21:37
 */
public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }


        }
        return null;

    }

    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first= new Node(key, value, first);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (Node x = first; x != null; x = x.next) {
            queue.add(x.key);
        }
        return queue;
    }

    public Value delete(Key key) {
        for (Node x = first; x != null; x = x.next) {
            Node temp = x.next;
            if (key.equals(x.key)) {
                if (first.next == null) {
                    first = null;
                    return x.value;
                }
                x = temp.next;
            }
            if (temp == null) {
                return null;
            }
           return get(temp.key);

        }
        return null;

    }
}
