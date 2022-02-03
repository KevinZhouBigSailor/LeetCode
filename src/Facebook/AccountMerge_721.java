package Facebook;

import java.util.*;

public class AccountMerge_721 {
    /**
     * https://leetcode.com/problems/accounts-merge/discuss/1601980/Java-Solution-using-UnionFind-Beats-99.87-of-submissions
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU();
        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;

        // prepare a hash with unique email address as key and index in accouts as value
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name.equals("")) {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                dsu.union(emailToID.get(email), emailToID.get(account.get(1)));
            }
        }
        // prepare a hash with index in accounts as key and list of unique email address for that account as value
        Map<Integer, List<String>> ans = new HashMap<>();
        for (String email : emailToName.keySet()) {
            int index = dsu.find(emailToID.get(email));
            ans.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }

        // collect the emails from idToEmails, sort it and add account name at index 0 to get the final list to add to final return List
        for (List<String> component : ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList(ans.values());
    }

    class DSU {
        int[] parent;

        public DSU() {
            parent = new int[10001];
            for (int i = 0; i <= 10000; ++i)
                parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}
