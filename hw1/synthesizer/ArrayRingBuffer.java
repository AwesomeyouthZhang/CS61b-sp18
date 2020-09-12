package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    private int first;    //index for the next dequeue or peek

    private int last;    //index for the next enqueue or peek

    private T[] rb;


    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.capacity = capacity;
        this.fillCount = 0;

    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    public int fillCount() {
        return fillCount;
    }


    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");

        }
        rb[last] = x;

        last = (last + 1) % capacity;
        fillCount += 1;

    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        first = (first + 1) % capacity;
        fillCount -= 1;
        return temp;


    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }


    @Override
    public Iterator<T> iterator() {
        return new arrayRingBufferIterator();
    }

    private class arrayRingBufferIterator implements Iterator<T> {
        int currentPos;
        int numRemain;
        arrayRingBufferIterator() {
            this.currentPos = first;
            this.numRemain = fillCount();
        }

        @Override
        public boolean hasNext() {
          return numRemain>0;

        }

        @Override
        public T next() {
            T item = rb[currentPos];
            currentPos = (currentPos+1) % capacity;
            numRemain --;

            return item;
        }
    }

}