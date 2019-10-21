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
            /*
            numOfTabs is the number of "\t", numOfTabs = 0
            when "\t" is not found, because s.lastIndexOf("\t") returns -1.
            So normally, the first parent "dir" have numOfTabs 0.
            */
            int numOfTabs = s.lastIndexOf("\t") + 1; //number of "\t"
            /* Level is defined as numOfTabs + 1.
            For example, in "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext",
            dir is level 1, subdir1 and subdir2 are level 2, file.ext is level3
            */
            int level = numOfTabs + 1;
            while (level < stack.size()) stack.pop();  // find parent;
            int len = stack.peek() + s.length() - numOfTabs + 1; // remove "/t", add"/"
            stack.push(len);
            if (s.contains(".")) maxLen = Math.max(maxLen, len - 1); // remove the last "/"
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

    public static void main(String[] args) {
        LongestAbsoluteFilePath instance = new LongestAbsoluteFilePath();
        instance.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
    }
}
