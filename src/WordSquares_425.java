import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSquares_425 {
    private TrieNode root = new TrieNode();

    private void addToTrie(String s, TrieNode root) {
        for (int i = 0; i < s.length(); i++) {
            int ind = s.charAt(i) - 'a';
            if (root.children[ind] == null) {
                root.children[ind] = new TrieNode();
            }
            root = root.children[ind];
        }
        root.word = s;
    }

    private void findAllSquares(int row, int col, TrieNode[] rows, List<List<String>> res) {
        if (row == rows.length) {
            List<String> temp = new ArrayList<>(rows.length);
            for (int i = 0; i < rows.length; i++) temp.add(rows[i].word);
            res.add(temp);
        } else if (col < rows.length) {
            TrieNode currow = rows[row];
            TrieNode curcol = rows[col];
            for (int i = 0; i < 26; i++) {
                if (currow.children[i] != null && curcol.children[i] != null) {
                    rows[row] = currow.children[i];
                    rows[col] = curcol.children[i];
                    findAllSquares(row, col + 1, rows, res);
                }
            }
            rows[row] = currow;
            rows[col] = curcol;
        } else if (col == rows.length){ // reach the end of column, go to the next row
            findAllSquares(row + 1, row + 1, rows, res);
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0 || words[0] == null || words[0].length() == 0) return res;
        for (String s : words) {
            addToTrie(s, root);
        }
        TrieNode[] rows = new TrieNode[words[0].length()];
        Arrays.fill(rows, root);
        findAllSquares(0, 0, rows, res);
        return res;
    }
}
