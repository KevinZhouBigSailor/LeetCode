package Facebook.Practice;

import java.util.*;

public class MergeIntervals_56 {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }

        intervals.sort((i1, i2) -> i1.start - i2.start);

        LinkedList<Interval> merged = new LinkedList<>();

        for (Interval interval : intervals) {
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            } else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        return merged;
    }
}
