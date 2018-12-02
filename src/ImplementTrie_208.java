/**
 * Created by zzhou on 4/5/2017.
 * mplement a trie with insert, search, and startsWith methods.

 Note:
 You may assume that all inputs are consist of lowercase letters a-z.
 */


public class ImplementTrie_208 {

    private TrieNode root;

    public ImplementTrie_208() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) {
                ws.children[c - 'a'] = new TrieNode();
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        ImplementTrie_208 obj = new ImplementTrie_208();
        obj.insert("cao");
        boolean param_2 = obj.search("cao");
        boolean param_3 = obj.startsWith("ca");

        System.out.println(param_2);
        System.out.println(param_3);
    }
}
