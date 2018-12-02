import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ApplePhoneInterviewII {
    public List<String[]> findCouplesInParty(String[] parties, String[][] couples) {
        HashSet<String> partySet = new HashSet<>();
        for (String party : parties) {
            partySet.add(party);
        }
        List<String[]> res = new ArrayList<>();
        for (String[] couple : couples) {
            if (partySet.contains(couple[0]) && partySet.contains(couple[1])) {
                res.add(couple);
            }
        }
        return res;
    }
}
