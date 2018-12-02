import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators_282 {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        backtracking(res, "", num, target, 0, 0, 0);
        return res;
    }

    public void backtracking(List<String> res, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
            if (target == eval) { //check overflow
                res.add(path);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                backtracking(res, path + cur, num, target, i + 1, cur, cur);
            } else {
                backtracking(res, path + "+" + cur, num, target, i + 1, eval + cur, cur);
                backtracking(res, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
                backtracking(res, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }
}

