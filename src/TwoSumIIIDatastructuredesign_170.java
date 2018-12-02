import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TwoSumIIIDatastructuredesign_170 {
    Map<Integer, Integer> hm;

    public void TwoSum() {
        hm = new HashMap<Integer, Integer>();
    }

    // Add the number to an internal data structure.
    public void add(int number) {
        hm.put(number, hm.getOrDefault(number, 0) + 1);
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        Iterator<Integer> iter = hm.keySet().iterator();
        while (iter.hasNext()) {
            int num1 = iter.next();
            int num2 = value - num1;
            if (hm.containsKey(num2)) {
                if (num1 != num2 || hm.get(num2) > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
