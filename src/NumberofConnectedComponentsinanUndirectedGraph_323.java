/**
 * Created by zzhou on 1/29/2018.
 */
public class NumberofConnectedComponentsinanUndirectedGraph_323 {
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) roots[i] = i;

        for(int[] e: edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            if (root1 != root2) {
                roots[root1] = root2; //Union
                n--;
            }
        }
        return n;
    }

    public int find(int[] roots, int id) {
        if (roots[id] == id) return id;
        roots[id] = find(roots, roots[id]);
        return roots[id];
    }
}
