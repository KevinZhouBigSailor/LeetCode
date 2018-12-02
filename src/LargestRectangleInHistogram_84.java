import java.util.Scanner;
import java.util.Stack;

/**
 * Created by zzhou on 4/3/2017.
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


 The largest rectangle is shown in the shaded area, which has area = 10 unit.

 For example,
 Given heights = [2,1,5,6,2,3],
 return 10.
 */
public class LargestRectangleInHistogram_84 {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> stk = new Stack<Integer>();
        int ret = 0;
        for (int i = 0; i <= len; ++i) {
            int h = (i == len ? 0 : heights[i]);
            if (stk.isEmpty() || h >= heights[stk.peek()]) {
                stk.push(i);
            } else {
                int top = stk.pop();
                ret = Math.max(ret, heights[top] * (stk.empty() ? i : i - stk.peek() - 1));
                --i;    // back one step again
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram_84 s = new LargestRectangleInHistogram_84();
        int[] input = {2, 1, 3, 4};
        System.out.println(s.largestRectangleArea(input));
    }
}
