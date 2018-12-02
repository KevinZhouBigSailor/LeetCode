import java.util.*;

/**
 * Created by zzhou on 3/1/2018.
 */
public class WordLadderII_126 {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        HashSet<String> dict = new HashSet<String>(wordList);
        if (dict == null)
            return res;
        Map<String, Integer> distance = new HashMap<String, Integer>();
        dict.add(end);
        distance.put(start, 1);

        Map<String, Set<String>> neighbours = new HashMap<String, Set<String>>();
        LinkedList<String> queue = new LinkedList<String>();

        queue.add(start);
        boolean foundIt = false;
        //BFS
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                String curword = queue.poll();

                Set<String> curneighbours = gettheNeibours(curword, dict);
                Iterator<String> iterator = curneighbours.iterator();
                while (iterator.hasNext()) {
                    String nei = iterator.next();
                    if (curword.equals(end)) {
                        foundIt = true;
                    }
                    if (!distance.containsKey(nei)) { // visited
                        distance.put(nei, distance.get(curword) + 1);
                        queue.add(nei);
                    } else {
                        if (distance.get(nei) != distance.get(curword) + 1)//if not shortest
                            iterator.remove(); //remove since not shortest
                    }

                }
                neighbours.put(curword, curneighbours);
            }
            if (foundIt)
                break;
        }
        //DFS
        List<String> worklist = new ArrayList<String>();
        dfs(start, end, dict, neighbours, worklist, res);
        return res;
    }

    public void dfs(String curword, String end, Set<String> dict, Map<String, Set<String>> neighbours, List<String> worklist, List<List<String>> res) {
        worklist.add(curword);
        if (curword.equals(end)) {
            res.add(new ArrayList<>(worklist));
        } else if (neighbours.containsKey(curword)) {
            for (String nei : neighbours.get(curword)) {
                dfs(nei, end, dict, neighbours, worklist, res);
            }
        }
        worklist.remove(worklist.size() - 1);
    }

    public Set<String> gettheNeibours(String curword, Set<String> dict) {
        Set<String> res = new HashSet<String>();

        int len = curword.length();
        char[] chs = curword.toCharArray();
        for (int i = 0; i < len; i++) {
            char old = chs[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chs[i] = c;
                String composStr = String.valueOf(chs);
                if (!composStr.equals(curword) && dict.contains(composStr))
                    res.add(composStr);
            }
            chs[i] = old;
        }
        return res;
    }
}
