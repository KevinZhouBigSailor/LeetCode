public class MinimumWindowSubsequence_727 {
    /**
     * 2-steps:
     *
     * Find a subsequence in S that contains T, and return ending index in S
     * Improve that subsequence by searching backwards from right-left to find best starting index in S
     * e.g.
     * i = 0 1 2 3 4 5 6 7 8 9
     * S = a b a c b c d f e g
     * T = bcde
     * find subsequence - bacbcdfe, end = 8
     * improve subsequence - bcdfe, start = 4
     * length = 5
     */
    public String minWindow(String s1, String s2) {
        char[] s = s1.toCharArray(), t = s2.toCharArray();
        String res = "";
        int i = 0, tindex = 0, slen = s.length, tlen = t.length, len = Integer.MAX_VALUE;
        while (i < slen) {
            if (s[i] == t[tindex]) {
                tindex++;
                if (tindex == tlen) { // all chars in T has been matched
                    int end = i + 1; //i is the last match index in S
                    tindex--; // now tindex is the last index in T
                    while (tindex >= 0) {  //both i and tindex move back
                        if (s[i] == t[tindex]) {
                            tindex--;
                        }
                        i--;
                    }
                    i++;  //we found the first match index in S
                    tindex++;  //now tindex == 0, the first match index in T
                    if (end - i < len) { //optimization ops
                        len = end - i;
                        res = s1.substring(i, end); // [i, end) is the matching subsequence
                    }
                }
            }
            i++;
        }
        return len == Integer.MAX_VALUE ? "" : res;
    }

    /**
     * M[i][j] means: for S(0~i) & T(0~j), the "largest" starting index in S which matches T.
     * It's the largest starting index, so for String T with length 1, if it matches S(i), it should be index i.
     */
    public String minWindow2(String S, String T) {
        int[][] M = new int[S.length()][T.length()];
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(0)) {
                M[i][0] = i;        //  largest starting index matches only first char in T
            } else {
                if (i == 0) {
                    M[i][0] = -1;    // S, T both have 1 char, if !match, fill -1 means we can't find a substring for S(0) & T(0)
                } else {
                    M[i][0] = M[i - 1][0];
                }
            }
        }
        for (int j = 1; j < T.length(); j++) {
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == T.charAt(j)) {
                    if (i == 0) {
                        M[i][j] = -1;       //  Actually, if j > i, M[i][j] is always -1, cause we cant find a substring of a shorter string matches a longer string
                    } else {
                        M[i][j] = M[i - 1][j - 1];  // As share2017 mentioned in his post
                    }
                } else {
                    if (i == 0) {
                        M[i][j] = -1;
                    } else {
                        M[i][j] = M[i - 1][j];
                    }
                }
            }
        }
        int start = 0;
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < S.length(); i++) {
            if (M[i][T.length() - 1] != -1) {
                if (i - M[i][T.length() - 1] + 1 < len) {
                    len = i - M[i][T.length() - 1] + 1;
                    start = M[i][T.length() - 1];
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : S.substring(start, start + len);
    }
}
