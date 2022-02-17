package Google.QuestionBank;

import java.util.*;

public class AddressBook {
    class TrieNode {
        public Map<String, TrieNode> children;
        public TrieNode() {
            children = new HashMap<>();
        }
    }

    class Address {
        String streetName;
        String cityName;
        String stateName;

        public Address(String a, String b, String c) {
            this.streetName = a;
            this.cityName = b;
            this.stateName = c;
        }
    }

    private TrieNode root = new TrieNode();

    public void buildTrie(Address query) {
        TrieNode curr = root;
        if (!curr.children.containsKey(query.stateName)) {
            curr.children.put(query.stateName, new TrieNode());
        }
        curr = curr.children.get(query.stateName);
        if (!curr.children.containsKey(query.cityName)) {
            curr.children.put(query.cityName, new TrieNode());
        }
        curr = curr.children.get(query.cityName);
        if (!curr.children.containsKey(query.streetName)) {
            curr.children.put(query.streetName, new TrieNode());
        }
        // curr = curr.children.get(query.streetName);
    }

    public boolean search(Address query) {
        return match(this.root, queryToArray(query), 0);
    }

    private boolean match(TrieNode curr, String[] query, int index) {
        if (index >= query.length) {
            return true;
        }
        if (query[index] != null) {
            if (!curr.children.containsKey(query[index])) return false;
        }
        for (String name : curr.children.keySet()) {
            if (match(curr.children.get(name), query, index + 1)) return true;
        }
        return false;
    }

    private String[] queryToArray(Address query) {
        String[] array = new String[3];
        array[0] = query.streetName;
        array[1] = query.cityName;
        array[2] = query.stateName;

        return array;
    }
}
