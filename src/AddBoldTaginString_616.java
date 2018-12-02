public class AddBoldTaginString_616 {
    public String addBoldTag(String s, String[] dict) {
        if (s.length() == 0) return s;
        boolean[] mask = new boolean[s.length()];
        for (String word : dict) {
            KMPSearch(word, s, mask);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            if (!mask[i]) {
                sb.append(s.charAt(i++));
            } else {
                sb.append("<b>");
                while (i < s.length() && mask[i]) {
                    sb.append(s.charAt(i++));
                }
                sb.append("</b>");
            }
        }
        return sb.toString();
    }

    private void KMPSearch(String pat, String txt, boolean[] mask) {
        int M = pat.length();
        int N = txt.length();

        int lps[] = new int[M];
        int j = 0;

        computeLPSArray(pat, M, lps);

        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                for (int k = i - j; k < i; k++) {
                    mask[k] = true;
                }
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    private void computeLPSArray(String pat, int M, int lps[]) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];

                } else  // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        AddBoldTaginString_616 instance = new AddBoldTaginString_616();
        String[] dicts = {"abc", "123"};
        instance.addBoldTag("abcxyz123", dicts);
    }
}
