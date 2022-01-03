package Google;

import java.util.LinkedList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new LinkedList<>();

        int wordLen = words.length;
        for (int i = 0, w; i < wordLen; i = w) {
            int len = -1;
            for (w = i; w < wordLen && len + words[w].length() + 1 <= maxWidth; w++) {
                len += words[w].length() + 1;
            }

            StringBuilder sb = new StringBuilder(words[i]);
            int space = 1, extra = 0;
            if (w != i + 1 && w != wordLen) { // not 1 char, not last line
                space = (maxWidth - len) / (w - i - 1) + 1;
                extra = (maxWidth - len) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) sb.append(' ');
                if (extra-- > 0) sb.append(' ');
                sb.append(words[j]);
            }
            int strLen = maxWidth - sb.length();
            while (strLen-- > 0) sb.append(' ');
            list.add(sb.toString());
        }
        return list;
    }
}
