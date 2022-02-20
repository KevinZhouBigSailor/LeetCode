package Google.QuestionBank;

import java.util.*;

public class MobileAPK_2121 {
    class Range {
        int min = 0;
        int max = Integer.MAX_VALUE;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public boolean hasMinSdk() {
            return min != 0;
        }

        public boolean hasMaxSdk() {
            return max != Integer.MAX_VALUE;
        }

        public int getMinApk() {
            return min;
        }

        public int getMaxApk() {
            return max;
        }
    }

    public List<Range> breakSpace(List<Range> apks) {
        Set<Integer> breaks = new TreeSet<>();
        for (Range apk : apks) {
            if (apk.hasMinSdk()) {
                breaks.add(apk.getMinApk() - 1);
            }
            if (apk.hasMaxSdk()) {
                breaks.add(apk.getMaxApk());
            }
        }
        // Make sure always add the start and the end breaks
        breaks.add(0);
        breaks.add(Integer.MAX_VALUE);
        Iterator<Integer> i = breaks.iterator();
        int nextStart = i.next() + 1;// safe because we added 1 element
        List<Range> result = new ArrayList<>();
        while (i.hasNext()) {
            int nextEnd = i.next();
            result.add(new Range(nextStart, nextEnd));
            // note when nextEnd is Integer.MAX_VALUE this overflow, but it doesn't matter, as it will be unused
            nextStart = nextEnd + 1;
        }
        return result;
    }
}
