import java.util.*;

public class AmazonOA_1 {
    public List<String> highestFrequentWord(String input, List<String> excludeList) {
        String[] words = input.split("[\\p{Punct}\\s]+");
        Set<String> excludeSet = new HashSet<>(excludeList);
        /*Map<String, Integer> map = new HashMap<>();
        TreeMap<Integer, Set<String>> tmap = new TreeMap<>(Collections.reverseOrder());
        Set<String> excludeSet = new HashSet<>(excludeList);
        for (String c : s) {
            if (map.containsKey(c)) {
                int count = map.get(c);
                map.put(c, count + 1);
                tmap.putIfAbsent(count + 1, new HashSet<>());
                tmap.getOrDefault(count + 1, new HashSet<>()).add(c);
                tmap.get(count).remove(c);
            } else if (!excludeSet.contains(c)) {
                map.put(c, 1);
                tmap.put(1, new HashSet<>());
                tmap.get(1).add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String output : tmap.firstEntry().getValue()) {
            sb.append(output);
        }
        return sb.toString();*/
        List<String> result = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i]))
                map.put(words[i], map.get(words[i]) + 1);
            else if (!excludeList.contains(words[i]))
                map.put(words[i], 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > 3)
                pq.poll();
        }

        while (!pq.isEmpty())
            result.add(0, pq.poll().getKey());

        return result;
    }

    public static void main(String[] args) {
        AmazonOA_1 instance = new AmazonOA_1();
        List<String> excludeList = new ArrayList<>();
        String[] exclude = {"a", "an", "the"};
        excludeList.addAll(Arrays.asList(exclude));
        System.out.println(instance.highestFrequentWord("Jimm'y has a table table, it is on a a the wood wood table", excludeList));
    }
}
