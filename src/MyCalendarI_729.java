package leetCode;

import java.util.TreeMap;

/**
 * Created by zzhou on 1/11/2018.
 */
public class MyCalendarI_729 {
    TreeMap<Integer, Integer> calendar;

    public void MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
