package Twitter;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        int end = intervals[0].end, prev = 0, count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prev].end > intervals[i].start) {
                if (intervals[prev].end > intervals[i].end) {
                    prev = i;
                }
                count++;
            } else {
                prev = i;
            }
        }
        return count;
    }

    //----------------------------------------------------

    class myComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.end - b.end;
        }
    }

    public boolean isOverlapping(Interval i, Interval j) {
        return i.end > j.start;
    }

    public int eraseOverlapIntervals2(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new myComparator());
        int dp[] = new int[intervals.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (!isOverlapping(intervals[j], intervals[i])) {
                    max = Math.max(dp[j], max);
                    break;
                }
            }
            dp[i] = Math.max(max + 1, dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return intervals.length - ans;
    }


}
