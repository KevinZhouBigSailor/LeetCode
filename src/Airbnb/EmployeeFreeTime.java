package Airbnb;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> schedule.get(a.eid).get(a.index).start - schedule.get(b.eid).get(b.index).start);
        int eid = 0, anchor = Integer.MAX_VALUE;
        for (List<Interval> employee : schedule) {
            pq.add(new Job(eid++, 0));
            anchor = Math.min(anchor, employee.get(0).start);
        }

        while (!pq.isEmpty()) {
            Job job = pq.poll();
            Interval iv = schedule.get(job.eid).get(job.index);
            if (anchor < iv.start) res.add(new Interval(anchor, iv.start));
            anchor = Math.max(anchor, iv.end);
            if (++job.index < schedule.get(job.eid).size()) {
                pq.add(job);
            }
        }

        return res;
    }

    class Job {
        int eid, index;

        Job(int e, int i) {
            eid = e;
            index = i;
        }
    }
}
