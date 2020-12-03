import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: ZhixianZhang
 * @time: 2020/11/18 19:10
 */


public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int N;  //the number of the pairs of key and value;
    private int M;  //the size of table
    private SequentialSearchST<Key, Value>[] st;    //存放链表对象的数组

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i += 1) {


            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        // 为啥要把hash值和0x7FFFFFFF做一次按位与操作呢，
        // 主要是为了保证得到的index的第一位为0，也就是为了得到一个正数。
        // 因为有符号数第一位0代表正数，1代表负数
        return key.hashCode() & 0x7fffffff % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (int i = 0; i < M; i += 1) {
            for (Key key : st[i].keys()) {
                queue.add(key);
            }

        }
        return queue;

    }

    public void resize(int capacity) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<>(capacity);
        for (int i = 0; i < M; i += 1) {
            for (Key key : st[i].keys()) {
                t.put(key, st[i].get(key));

            }

        }
        this.M = t.M;
        this.N = t.N;
        this.st = t.st;
    }

    public Value delete(Key key) {
        Value temp = null;
        for (int i = 0; i < M; i += 1) {

            temp = st[i].delete(key);
            if (temp != null) {
                break;
            }


        }
        return temp;

    }





}
