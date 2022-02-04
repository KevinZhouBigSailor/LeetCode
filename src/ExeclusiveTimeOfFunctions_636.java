import Facebook.ExclusiveTimeOfFunctions_636;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class ExeclusiveTimeOfFunctions_636 {
    public int[] exclusiveTime(int n, List< String > logs) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];
        String[] s = logs.get(0).split(":");
        stack.push(Integer.parseInt(s[0]));
        int i = 1, prev = Integer.parseInt(s[2]);
        while (i < logs.size()) {
            s = logs.get(i).split(":");
            if (s[1].equals("start")) {
                if (!stack.isEmpty())
                    res[stack.peek()] += Integer.parseInt(s[2]) - prev;
                stack.push(Integer.parseInt(s[0]));
                prev = Integer.parseInt(s[2]);
            } else {
                res[stack.peek()] += Integer.parseInt(s[2]) - prev + 1;
                stack.pop();
                prev = Integer.parseInt(s[2]) + 1;
            }
            i++;
        }
        return res;
    }

    public int[] exclusiveTime2(int n, List<String> logs) {
        Deque<ExclusiveTimeOfFunctions_636.Log> stack = new ArrayDeque<>();
        int[] result = new int[n];
        int duration = 0;
        for (String content : logs) {
            ExclusiveTimeOfFunctions_636.Log log = new ExclusiveTimeOfFunctions_636.Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                ExclusiveTimeOfFunctions_636.Log top = stack.pop();
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
