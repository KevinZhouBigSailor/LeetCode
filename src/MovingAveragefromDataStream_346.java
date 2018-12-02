/**
 * Created by zzhou on 2/15/2018.
 */
public class MovingAveragefromDataStream_346 {
    private int[] window;
    private int n, insert;
    private long sum;

    /**
     * Initialize your data structure here.
     */
    public MovingAveragefromDataStream_346(int size) {
        window = new int[size];
        insert = 0;
        sum = 0;
    }

    public double next(int val) {
        if (n < window.length) n++;
        sum -= window[insert];
        sum += val;
        window[insert] = val;
        insert = (insert + 1) % window.length;

        return (double) sum / n;
    }
}
