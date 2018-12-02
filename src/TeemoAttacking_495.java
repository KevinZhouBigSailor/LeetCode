/**
 * Created by zzhou on 9/11/2017.
 */
public class TeemoAttacking_495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration == 0) return 0;

        int res = 0, start = timeSeries[0], end = timeSeries[0] + duration;
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] > end) {
                res += end - start;
                start = timeSeries[i];
            }
            end = timeSeries[i] + duration;
        }
        return res += end - start;
    }
}
