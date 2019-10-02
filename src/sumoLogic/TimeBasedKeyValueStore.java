package sumoLogic;

import java.util.*;

public class TimeBasedKeyValueStore {
    class TimeMap {
        Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap();
        }

        public void set(String key, String value, int timestamp) {

            map.putIfAbsent(key, new TreeMap());
            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";

            TreeMap<Integer, String> tree = map.get(key);
            Integer t = tree.floorKey(timestamp);
            return t != null ? tree.get(t) : "";
        }
    }


    class TimeMap2 {
        class Node {
            String value;
            int timestamp;

            Node(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        Map<String, List<Node>> map;

        /**
         * Initialize your data structure here.
         */
        public TimeMap2() {
            map = new HashMap();
        }

        public void set(String key, String value, int timestamp) {
            map.putIfAbsent(key, new ArrayList());
            map.get(key).add(new Node(value, timestamp));
        }

        public String get(String key, int timestamp) {
            List<Node> nodes = map.get(key);
            if (nodes == null) return "";

            int left = 0, right = nodes.size() - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                Node node = nodes.get(mid);
                if (node.timestamp == timestamp) {
                    return node.value;
                } else if (node.timestamp < timestamp) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            if (nodes.get(right).timestamp <= timestamp) return nodes.get(right).value;
            else if (nodes.get(left).timestamp <= timestamp) return nodes.get(left).value;
            return "";
        }
    }
}
