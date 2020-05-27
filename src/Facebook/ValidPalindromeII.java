package Facebook;

public class ValidPalindromeII {
    public boolean isPalindrome(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        if (s.length() == 0) return false;
        for (int i = 0; i < s.length() / 2; i++) {
            int j = s.length() - 1 - i;
            if (s.charAt(i) != s.charAt(j)) {
                return (isPalindrome(s, i + 1, j) ||
                        isPalindrome(s, i, j - 1));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindromeII instance = new ValidPalindromeII();
        String s = "abc";
        System.out.println(instance.validPalindrome(s));
    }
}
