package Facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses_301 {
    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0) rmL--;
                else rmR++;
            }
        }
        Set<String> set = new HashSet<>();
        dfs(s, 0, set, new StringBuilder(), rmL, rmR, 0);
        return new ArrayList<String>(set);
    }

    public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
        if (rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) {
                res.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(i);
        int len = sb.length();

        if (c == '(') {
            dfs(s, i + 1, res, sb, rmL - 1, rmR, open);
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);
        } else if (c == ')') {
            dfs(s, i + 1, res, sb, rmL, rmR - 1, open);
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);
        } else {
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);
        }
        sb.setLength(len);
    }
}
