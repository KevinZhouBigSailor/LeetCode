package WeWork;

import java.util.*;

public class DesignLogStorageSystem {
    TreeMap<Long, Integer> map;

    static Map<String, Integer> timeMap = Collections.unmodifiableMap(new HashMap<String, Integer>() {
        {
            timeMap.put("Year", 0);
            timeMap.put("Month", 1);
            timeMap.put("Day", 2);
            timeMap.put("Hour", 3);
            timeMap.put("Minute", 4);
            timeMap.put("Second", 5);
        }
    });

    public void LogSystem() {
        map = new TreeMap<>();
    }

    public void put(int id, String timeStamp) {
        int st[] = Arrays.stream(timeStamp.split(":")).mapToInt(Integer::parseInt).toArray();
        map.put(convert(st), id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        ArrayList<Integer> res = new ArrayList<>();
        long start = granularity(s, gra, false);
        long end = granularity(e, gra, true);
        for (long key : map.tailMap(start).keySet()) {
            if (key < end) {
                res.add(map.get(key));
            }
        }
        return res;
    }

    private long convert(int[] st) {
        st[1] -= (st[1] == 0 ? 0 : 1);
        st[2] -= (st[2 == 0 ? 0 : 1]);
        return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
    }

    public long granularity(String s, String gra, boolean end) {
        String[] res = new String[]{"1999", "00", "00", "00", "00", "00"};
        String[] st = s.split(":");
        for (int i = 0; i <= timeMap.get(gra); i++) {
            res[i] = st[i];
        }
        int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
        if (end) {
            t[timeMap.get(gra)]++;
        }
        return convert(t);
    }
}
