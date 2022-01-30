package Facebook;

/**
 * Created by zzhou on 4/4/2017.
 */
public class FindPeakElement_162 {
    public int findPeakElement(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public int helper(int[] num, int start, int end) {
        if (start == end) {
            return start;
        } else if (start + 1 == end) {
            if (num[start] > num[end]) return start;
            return end;
        } else {

            int m = start + (end - start) / 2;

            if (num[m] > num[m - 1] && num[m] > num[m + 1]) {

                return m;

            } else if (num[m - 1] > num[m] && num[m] > num[m + 1]) {

                return helper(num, start, m - 1);

            } else {

                return helper(num, m + 1, end);

            }

        }
    }

    public static void main(String[] args) {
        FindPeakElement_162 s = new FindPeakElement_162();
        int[] input = {2, 4, 3, 5};
        System.out.println(s.findPeakElement(input));
    }
}
