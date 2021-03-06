/**
 * Created by zzhou on 6/15/2017.
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

 Note:
 n is positive and will fit within the range of a 32-bit signed integer (n < 231).

 Example 1:

 Input:
 3

 Output:
 3
 Example 2:

 Input:
 11

 Output:
 0

 Explanation:
 The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class NthDigit_400 {
    public int findNthDigit(int n) {
        n -= 1;
        int digits = 1, len = 1;
        while (n / 9 / len / digits >= 1) {
            n -= 9 * len * digits;
            digits++;
            len *= 10;
        }
        int num = len + n/digits;
        return Character.getNumericValue(Integer.toString(num).charAt(n%digits));
    }

    public static void main(String[] args) {
        NthDigit_400 instance = new NthDigit_400();
        System.out.println(instance.findNthDigit(15));
    }
}
