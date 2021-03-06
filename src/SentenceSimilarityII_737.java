import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzhou on 2/23/2018.
 */
public class SentenceSimilarityII_737 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, String> m = new HashMap<>();
        for (String[] p : pairs) {
            String parent1 = find(m, p[0]), parent2 = find(m, p[1]);
            union(parent1, parent2, m);
        }

        for (int i = 0; i < words1.length; i++)
            if (!words1[i].equals(words2[i]) && !find(m, words1[i]).equals(find(m, words2[i]))) return false;

        return true;
    }

    private void union(String parent1, String parent2, Map<String, String> m) {
        if (!parent1.equals(parent2)) m.put(parent1, parent2);
    }

    private String find(Map<String, String> m, String s) {
        if (!m.containsKey(s)) m.put(s, s);
        return s.equals(m.get(s)) ? s : find(m, m.get(s));
    }
}
