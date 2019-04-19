package Airbnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PyramidTransitionMatrix {

    private Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {

        for (String edge : allowed) {
            int a = edge.charAt(0) - 'A';
            int b = edge.charAt(1) - 'A';
            int c = edge.charAt(2) - 'A';

            map.putIfAbsent(a, new HashMap<>());
            map.get(a).putIfAbsent(b, new ArrayList<>());
            map.get(a).get(b).add(c);
        }

        int N = bottom.length();
        int[][] A = new int[N][N];
        int t = 0;
        for (char c : bottom.toCharArray()) {
            A[N - 1][t++] = c - 'A';
        }
        return solve(A, N - 1, 0);
    }

    public boolean solve(int[][] A, int N, int i) {
        if (N == 1 && i == 1) {
            return true;
        } else if (i == N) {
            solve(A, N - 1, 0);
        } else if (map.containsKey(A[N][i]) && map.get(A[N][i]).containsKey(A[N][i + 1])) {
            List<Integer> w = map.get(A[N][i]).get(A[N][i + 1]);
            for (Integer b : w) {
                A[N - 1][i] = b;
                if (solve(A, N, i + 1)) return true;
            }
        }
        return false;
    }
}
