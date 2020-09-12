package synthesizer;

public class GuitarString {
    //sampling rate
    private static final int SR = 44100;
    //energy decay factor
    private static final double DECAY = 0.996;
    /*buffer for storing sound data*/
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        //Math.round()当传入float 型，得到int，传入double得到long
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        //初始化

        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.enqueue(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.capacity(); i += 1) {
            //Math.random() 范围为【0-1】，减去0.5则为【-0.5，0.5】
            double r = Math.random() - 0.5;
            //一定别忘了先 dequeue
            buffer.dequeue();
            buffer.enqueue(r);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        buffer.enqueue(DECAY * (buffer.dequeue() + buffer.peek()) / 2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }

}
