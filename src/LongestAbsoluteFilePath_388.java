import java.util.Stack;

/**
 * Created by zzhou on 2/14/2018.
 */
public class LongestAbsoluteFilePath_388 {
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1; //number of "\t"
            while (level + 1 < stack.size()) stack.pop();  // find parent;
            int len = stack.peek() + s.length() - level + 1; // remove "/t", add"/"
            stack.push(len);
            if(s.contains(".")) maxLen = Math.max(maxLen, len - 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestAbsoluteFilePath_388 instance = new LongestAbsoluteFilePath_388();
        instance.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
    }
}
