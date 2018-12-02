public class PalindromicSubstrings_647 {
    int count = 0;

    public int countSubstrings(String S) {
        if (S == null || S.length() == 0) return 0;
        for (int i = 0; i < S.length(); i++) {
            extendPalindrome(S, i, i);
            extendPalindrome(S, i, i + 1);
        }
        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}
