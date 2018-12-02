import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZigzagIterator_281 {
    List<Iterator<Integer>> itrs;
    int idx;

    public ZigzagIterator_281(List<Integer> v1, List<Integer> v2) {
        itrs = new ArrayList<Iterator<Integer>>();
        itrs.add(v1.iterator());
        itrs.add(v2.iterator());
        idx = 0;
    }

    public int next() {
        hasNext();
        int val = itrs.get(idx).next();
        idx = (idx + 1) % itrs.size();
        return val;
    }

    public boolean hasNext() {
        if (itrs.size() == 0)
            return false;
        else if (itrs.get(idx).hasNext())
            return true;
        else {
            do {
                itrs.remove(idx);
                if (itrs.size() == 0)
                    return false;
                idx %= itrs.size();
            } while (!itrs.get(idx).hasNext());
            return true;
        }
    }
}
