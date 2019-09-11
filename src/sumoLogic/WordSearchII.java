package sumoLogic;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, root, i, j, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, TrieNode p, int i, int j, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.children[c - 'a'] == null) return;
        p = p.children[c - 'a'];
        if (p.isWord) { // found one
            res.add(p.word);
            p.isWord = false; // de-duplicate
        }
        board[i][j] = '#';
        if (i > 0) dfs(board, p, i - 1, j, res);
        if (j > 0) dfs(board, p, i, j - 1, res);
        if (i < board.length - 1) dfs(board, p, i + 1, j, res);
        if (j < board[0].length - 1) dfs(board, p, i, j + 1, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) p.children[i] = new TrieNode();
                p = p.children[i];
            }
            p.isWord = true;
            p.word = word;
        }
        return root;
    }
}
