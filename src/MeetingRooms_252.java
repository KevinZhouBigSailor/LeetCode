import java.util.PriorityQueue;

public class MeetingRooms_252 {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) return true;
        PriorityQueue<Interval> queue = new PriorityQueue<>((a, b) -> a.start - b.start);
        for (Interval interval : intervals) {
            queue.add(interval);
        }
        Interval one = queue.poll();
        while (!queue.isEmpty()) {
            Interval two = queue.peek();
            if (one.end > two.start) return false;
            one = queue.poll();
        }
        return true;
    }
}
