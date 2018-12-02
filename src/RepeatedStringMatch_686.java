public class RepeatedStringMatch_686 {
    public int repeatedStringMatch(String A, String B) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.indexOf(B) >= 0) return count;
        if (sb.append(A).indexOf(B) >= 0) return count + 1;
        return -1;
    }
}
