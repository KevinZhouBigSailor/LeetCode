class UnionFind {
    int[] father;
    int m, n;
    int count = 0;

    UnionFind(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        father = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                father[id] = id;
                count++;
            }
        }
    }

    public void union(int node1, int node2) {
        int find1 = find(node1);
        int find2 = find(node2);
        if (find1 != find2) {
            father[node1] = node2;
            count--;
        }
    }

    public int find(int node) {
        if (father[node] == node) return node;
        father[node] = find(father[node]);
        return father[node];
    }
}