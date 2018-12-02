/**
 * Created by zzhou on 3/28/2018.
 */
public class HIndex_274 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        for (int c : citations) {
            papers[Math.min(n, c)]++;
        }
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }
}
