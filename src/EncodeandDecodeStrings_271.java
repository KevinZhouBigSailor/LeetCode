import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzhou on 2/20/2018.
 */
public class EncodeandDecodeStrings_271 {
    public String encode(List<String> strs) {
        return strs.stream()
                .map(s -> s.replace("/", "//").replace("*", "/*") + "*")
                .collect(Collectors.joining());
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') {
                sb.append(s.charAt(++i));
            } else if (s.charAt(i) == '*') {
                res.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return res;
    }
}
