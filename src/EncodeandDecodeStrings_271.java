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


    public class Codec {
        // Encodes string length to bytes string
        public String intToString(String s) {
            int x = s.length();
            char[] bytes = new char[4];
            for (int i = 3; i > -1; --i) {
                bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
            }
            return new String(bytes);
        }

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (String s : strs) {
                sb.append(intToString(s));
                sb.append(s);
            }
            return sb.toString();
        }

        // Decodes bytes string to integer
        public int stringToInt(String bytesStr) {
            int result = 0;
            for (char b : bytesStr.toCharArray())
                result = (result << 8) + (int) b;
            return result;
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            int i = 0, n = s.length();
            List<String> output = new ArrayList();
            while (i < n) {
                int length = stringToInt(s.substring(i, i + 4));
                i += 4;
                output.add(s.substring(i, i + length));
                i += length;
            }
            return output;
        }
    }
}
