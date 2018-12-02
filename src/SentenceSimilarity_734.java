import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzhou on 3/28/2018.
 */
public class SentenceSimilarity_734 {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;

        Set<String> pairset = new HashSet<>();
        for (String[] pair : pairs) {
            pairset.add(pair[0] + "#" + pair[1]);
        }

        for (int i = 0; i < words1.length; i++) {
            if (!words1[i].equals(words2[i]) &&
                    !pairset.contains(words1[i] + "#" + words2[i]) &&
                    !pairset.contains(words2[i] + "#" + words1[i]))
                return false;
        }
        return true;
    }
}
