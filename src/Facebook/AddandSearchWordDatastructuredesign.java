package Facebook;

public class AddandSearchWordDatastructuredesign {
    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.word = word;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chars, int k, TrieNode node) {
        if (k == chars.length) return node.word != null;
        if (chars[k] != '.') {
            return node.children[chars[k] - 'a'] != null && match(chars, k + 1, node.children[chars[k] - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (match(chars, k + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

