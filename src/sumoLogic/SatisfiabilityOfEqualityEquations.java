package sumoLogic;

import java.util.ArrayList;
import java.util.Stack;

public class SatisfiabilityOfEqualityEquations {
    public boolean equationsPossible(String[] equations) {
        ArrayList<Integer>[] graph = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            graph[i] = new ArrayList();

        for (String eqn : equations) {
            if (eqn.charAt(1) == '=') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                graph[x].add(y);
                graph[y].add(x);
            }
        }

        int[] color = new int[26];
        int t = 0;
        for (int start = 0; start < 26; ++start) {
            if (color[start] == 0) {
                t++;
                Stack<Integer> stack = new Stack();
                stack.push(start);
                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    for (int nei : graph[node]) {
                        if (color[nei] == 0) {
                            color[nei] = t;
                            stack.push(nei);
                        }
                    }
                }
            }
        }

        for (String eqn : equations) {
            if (eqn.charAt(1) == '!') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                if (x == y || color[x] != 0 && color[x] == color[y])
                    return false;
            }
        }

        return true;
    }

    int[] uf = new int[26];
    public boolean equationsPossibleUnionFind(String[] equations) {
        for (int i = 0; i < 26; ++i) uf[i] = i;
        for (String e : equations)
            if (e.charAt(1) == '=')
                uf[find(e.charAt(0) - 'a')] = find(e.charAt(3) - 'a');
        for (String e : equations)
            if (e.charAt(1) == '!' && find(e.charAt(0) - 'a') == find(e.charAt(3) - 'a'))
                return false;
        return true;
    }

    public int find(int x) {
        if (x != uf[x]) uf[x] = find(uf[x]);
        return uf[x];
    }
}
