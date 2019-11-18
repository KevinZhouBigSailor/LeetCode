package Twitter;

import java.util.*;

/*
 * We want to measure a metric called User Active Minutes (UAM).  User active minutes for a given user is defined as the count of the number of distinct minutes in which the user takes some action on Twitter.  Multiple actions in the same minute are only counted as one minute.  We would like a histogram of the number of users who spend X minutes on Twitter, for different values of X, given 30 days of raw logs and an interval size in minutes.

The raw logs are in the format: [user_id, epoch timestamp].  Each row represents an action a user took on Twitter.  The logs are ordered chronologically.  Duplicates are possible.

Write code to compute the histogram of UAMs across our user base.

Example:
Raw logs

```
[1, 1518290973]
[2, 1518291032]
[3, 1518291095]
[1, 1518291096]
[4, 1518291120]
[3, 1518291178]
[1, 1518291200]
[1, 1518291200]
```
```
Interval size
2

Resulting histogram
[2, 2]

2 users spend 0 - 1 minutes on Twitter
2 users spend 2 - 3 minutes on Twitter
 */
public class RawLogs {
    public int[] processLogs(List<RawLog> rawLogs, Integer intervalSize) {
        HashMap<Integer, HashSet<Integer>> uams = new HashMap<>();
        for (RawLog log : rawLogs) {
            int min = log.getMin();
            HashSet<Integer> mins = uams.getOrDefault(log.getUserId(), new HashSet<>());
            mins.add(min);
            uams.put(log.getUserId(), mins);
        }
        int[] res = new int[100000];
        Arrays.fill(res, 0);
        for (Integer userId : uams.keySet()) {
            res[uams.get(userId).size() / intervalSize]++;
        }
        return res;
    }
}

class RawLog {
    Integer userId;
    Long TimeStamp;

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(this.getTimeStamp());
        int min = calendar.get(Calendar.MINUTE);
        return userId + min * 100000;
    }

    public Integer getMin() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(this.getTimeStamp());
        return calendar.get(Calendar.MINUTE);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        TimeStamp = timeStamp;
    }
}
