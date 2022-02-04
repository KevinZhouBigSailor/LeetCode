package Facebook;

import java.util.*;

public class ExclusiveTimeOfFunctions_636 {
    /**
     * Extract the log parsing logic as a inner class.
     * Calculate the function's running time when encounter an "end" log entry. If current ended func has a main func still running (in the stack), substract the running time advance. So we don't need to use a "prev" variable.
     * Another idea is using a field in the inner class to track the real running time for a function. I believe this way would be the most straightforward for myself.
     * Both methods follow the O(n) time complexly, and O(n/2) extra space consumption.
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Log> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for (String content : logs) {
            Log log = new Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                result[top.id] += (log.time - top.time + 1);
                if (!stack.isEmpty()) {
                    result[stack.peek().id] -= (log.time - top.time + 1);
                }
            }
        }

        return result;
    }

    public int[] exclusiveTime2(int n, List<String> logs) {
        Deque<Log> stack = new ArrayDeque<>();
        int[] result = new int[n];
        int duration = 0;
        for (String content : logs) {
            Log log = new Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                result[top.id] += (log.time - top.time + 1 - top.subDuration);
                if (!stack.isEmpty()) {
                    stack.peek().subDuration += (log.time - top.time + 1);
                }
            }
        }

        return result;
    }

    public static class Log {
        public int id;
        public boolean isStart;
        public int time;
        public int subDuration;

        public Log(String content) {
            String[] strs = content.split(":");
            id = Integer.valueOf(strs[0]);
            isStart = strs[1].equals("start");
            time = Integer.valueOf(strs[2]);
            subDuration = 0;
        }
    }
}
