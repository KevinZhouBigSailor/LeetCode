package Facebook;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzhou on 12/14/2017.
 */
public class MergeIntervals_56 {
    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // Sort by ascending starting point using an anonymous Comparator
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
