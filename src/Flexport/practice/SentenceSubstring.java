package Flexport.practice;

import java.util.*;

public class SentenceSubstring {
    public static List<String> generateRandom(String input, int n, int m) {
        String[] array = input.split(" ");
        Map<String, List<Integer>> map = populateMap(array, m);
        int index = new Random().nextInt(array.length);
        List<String> result = new ArrayList<>();
        result.addAll(getKey(array, index, m));
        while (result.size() != n) {
            List<String> key = new ArrayList<>();
            for (int i = result.size() - m; i < result.size(); i++) {
                key.add(result.get(i));
            }
            List<Integer> nextIndexes = map.get(String.join(" ", key));
            index = nextIndexes.get(new Random().nextInt(nextIndexes.size()));
            result.add(array[index]);
        }
        return result;
    }

    public static Map<String, List<Integer>> populateMap(String[] strings, int m) {
        Map<String, List<Integer>> result = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            String key = String.join(" ", getKey(strings, i, m));
            int nextIndex = (i + m) % strings.length;
            if (!result.containsKey(key)) {
                result.put(key, new ArrayList<>());
            }
            result.get(key).add(nextIndex);
        }
        return result;
    }

    public static List<String> getKey(String[] strings, int currentIndex, int m) {
        List<String> result = new ArrayList<>();
        int index = currentIndex;
        while (result.size() < m) {
            result.add(strings[index++ % strings.length]);
        }
        return result;
    }
}
