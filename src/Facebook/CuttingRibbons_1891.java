package Facebook;

public class CuttingRibbons_1891 {
    // https://leetcode.com/problems/cutting-ribbons/discuss/1263437/Java-Simple-Binary-Search-+-Explanation/1003412
    public int maxLength(int[] ribbons, int k) {
        int max = Integer.MIN_VALUE;
        for (int len : ribbons)
            max = Math.max(max, len);

        int n = ribbons.length, low = 1, high = max, mid, count;
        while (low <= high) {
            mid = low + (high - low) / 2;

            count = 0;
            for (int length : ribbons)
                count += length / mid;

            if (count < k)
                high = mid - 1;
            else
                low = mid + 1;
        }

        return high > 0 ? high : 0;
    }
}
