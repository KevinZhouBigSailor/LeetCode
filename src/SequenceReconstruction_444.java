import java.util.*;

/**
 * Created by zzhou on 12/15/2017.
 */
public class SequenceReconstruction_444 {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> degree = new HashMap<>();

        for (int i = 0; i < seqs.size(); i++) {
            List<Integer> seq = seqs.get(i);
            if(seq.size() == 1) {
                if(!map.containsKey(seq.get(0))) {
                    map.put(seq.get(0), new HashSet<>());
                    degree.put(seq.get(0), 0);
                }
            } else {
                for (int j = 0; j < seq.size() - 1; j++) {
                    if (!map.containsKey(seq.get(j))) {
                        map.put(seq.get(j), new HashSet<>());
                        degree.put(seq.get(j), 0);
                    }

                    if (!map.containsKey(seq.get(j + 1))) {
                        map.put(seq.get(j + 1), new HashSet<>());
                        degree.put(seq.get(j + 1), 0);
                    }

                    if (map.get(seq.get(j)).add(seq.get(j + 1))) {
                        degree.put(seq.get(j + 1), degree.get(seq.get(j + 1)) + 1);
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: degree.entrySet()) {
            if(entry.getValue() == 0) queue.offer(entry.getKey());
        }

        int index = 0;
        while(!queue.isEmpty()) {
            if(queue.size() > 1) return false;
            int curr = queue.poll();
            if(index == org.length || curr != org[index++]) return false;
            for(int next: map.get(curr)) {
                degree.put(next, degree.get(next) - 1);
                if(degree.get(next) == 0) queue.offer(next);
            }
        }
        return index == org.length && index == map.size();
    }
}
