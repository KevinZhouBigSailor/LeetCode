import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals_435 {
        class myComparator implements Comparator<Interval> {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        }
        public int eraseOverlapIntervals(Interval[] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            Arrays.sort(intervals, new myComparator());
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

}
