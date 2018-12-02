/**
 * Created by zzhou on 5/23/2017.
 */
public class H_Index_274 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for(int c : citations) {
            if(c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        H_Index_274 instance = new H_Index_274();
        int[] nums = {3, 0, 6, 5, 1};
        System.out.println(instance.hIndex(nums));
    }
}
