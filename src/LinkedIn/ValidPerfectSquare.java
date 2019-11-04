package LinkedIn;

/**
 * Created by zzhou on 6/7/2017.
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * <p>
 * Note: Do not use any built-in library function such as sqrt.
 * <p>
 * Example 1:
 * <p>
 * Input: 16
 * Returns: True
 * Example 2:
 * <p>
 * Input: 14
 * Returns: False
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            long mid = (low + high) / 2;
            long square = mid * mid;
            if (square == num) {
                return true;
            } else if (square < num) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return false;
    }

    class Solution {
        public boolean isPerfectSquare(int num) {
            if (num < 2) return true;

            long x = num / 2;
            while (x * x > num) {
                x = (x + num / x) / 2; //newton's method
            }
            return (x * x == num);
        }
    }
}
