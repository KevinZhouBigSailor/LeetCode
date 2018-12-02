import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzhou on 2/14/2018.
 */
public class FindAnagramMappings_760 {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            ans[i] = map.get(A[i]);
        }
        return ans;
    }
}
