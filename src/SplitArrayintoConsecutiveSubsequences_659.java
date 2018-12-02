import java.util.HashMap;

public class SplitArrayintoConsecutiveSubsequences_659 {
    public boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        HashMap<Integer, Integer> appendFreq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (freq.get(i) == 0) continue;
            else if (appendFreq.getOrDefault(i, 0) > 0) {
                appendFreq.put(i, appendFreq.get(i) - 1);
                appendFreq.put(i + 1, appendFreq.getOrDefault(i + 1, 0) + 1);
            } else if (freq.getOrDefault(i + 1, 0) > 0 && freq.getOrDefault(i + 2, 0) > 0) {
                freq.put(i + 1, freq.get(i + 1) - 1);
                freq.put(i + 2, freq.get(i + 2) - 1);
                appendFreq.put(i + 3, appendFreq.getOrDefault(i + 3, 0) + 1);
            } else return false;
            freq.put(i, freq.get(i) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayintoConsecutiveSubsequences_659 instance = new SplitArrayintoConsecutiveSubsequences_659();
        int[] nums = {1,2,3,4,4,5};
        System.out.println(instance.isPossible(nums));
    }
}

