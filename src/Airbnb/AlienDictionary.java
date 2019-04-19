package Airbnb;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        String result = "";
        if (words == null || words.length == 0) return result;
        for (String s : words) {
            for (char c : s.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            int length = Math.min(curr.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = curr.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = map.getOrDefault(c1, new HashSet<>());
                    if(!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for(char c: inDegree.keySet()) {
            if (inDegree.get(c) == 0) queue.add(c);
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            result += c;
            if(map.containsKey(c)) {
                for(char c2: map.get(c)) {
                    inDegree.put(c2, inDegree.get(c2) - 1);
                    if(inDegree.get(c2) == 0) queue.add(c2);
                }
            }
        }
        if(result.length() != inDegree.size()) return "";
        return result;
    }
}
