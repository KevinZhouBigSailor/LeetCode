import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/swap-adjacent-in-lr-string/discuss/217070/Python-using-corresponding-position-
 */

public class SwapAdjacentInLRString_777 {
    private class Pair {
        int i;
        char c;

        Pair(int i, char c) {
            this.i = i;
            this.c = c;
        }
    }

    public boolean canTransform(String start, String end) {
        List<Pair> startPairs = new ArrayList<>();
        List<Pair> endPairs = new ArrayList<>();
        for (int i = 0; i < Math.max(start.length(), end.length()); i++) {
            if (i < start.length() && start.charAt(i) != 'X') {
                startPairs.add(new Pair(i, start.charAt(i)));
            }
            if (i < end.length() && end.charAt(i) != 'X') {
                endPairs.add(new Pair(i, end.charAt(i)));
            }
        }

        if (startPairs.size() != endPairs.size()) return false;
        for (int i = 0; i < startPairs.size(); i++) {
            if (startPairs.get(i).c != endPairs.get(i).c) return false;
            if (startPairs.get(i).c == 'L' && startPairs.get(i).i < endPairs.get(i).i) return false;
            if (startPairs.get(i).c == 'R' && startPairs.get(i).i > endPairs.get(i).i) return false;
        }

        return true;
    }
}
