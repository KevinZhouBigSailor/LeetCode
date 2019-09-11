package sumoLogic;

import java.util.Stack;

/**
 * Created by zzhou on 2/14/2018.
 */
public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1; //number of "\t"
            while (level + 1 < stack.size()) stack.pop();  // find parent;
            int len = stack.peek() + s.length() - level + 1; // remove "/t", add"/"
            stack.push(len);
            if (s.contains(".")) maxLen = Math.max(maxLen, len - 1);
        }
        return maxLen;
    }

    public int lengthLongestPath2(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length + 1];
        int maxLen = 0;
        for (String s : paths) {
            int lev = s.lastIndexOf("\t") + 1, curLen = stack[lev + 1] = stack[lev] + s.length() - lev + 1;
            if (s.contains(".")) maxLen = Math.max(maxLen, curLen - 1);
        }
        return maxLen;
    }
}
