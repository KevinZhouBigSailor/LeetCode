import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences_187 {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String st = s.substring(i, i + 10);
            if (!seen.add(st)) repeated.add(st);
        }
        return new ArrayList<>(repeated);
    }
}
