package Facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzhou on 4/20/2017.
 */
public class TrieNode {
    public boolean isWord;
    public TrieNode[] children;
    public List<String> startwith;
    public String word;
    int weight;

    public TrieNode() {
        children = new TrieNode[26];
        startwith = new ArrayList<>();
    }
}
