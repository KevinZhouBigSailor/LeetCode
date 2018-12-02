package leetCode;

import java.util.*;

/**
 * Created by zzhou on 1/10/2018.
 */
public class PyramidTransitionMatrix_756 {
    /*int[][] T;*/
    private Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>();
    //Set<Long> seen;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        /*T = new int[7][7];
        for (String a: allowed)
            T[a.charAt(0) - 'A'][a.charAt(1) - 'A'] |= 1 << (a.charAt(2) - 'A');*/

        for (String edge : allowed) {
            int a = edge.charAt(0) - 'A';
            int b = edge.charAt(1) - 'A';
            int c = edge.charAt(2) - 'A';

            map.putIfAbsent(a, new HashMap<>());
            map.get(a).putIfAbsent(b, new ArrayList<>());
            map.get(a).get(b).add(c);
        }

        //seen = new HashSet();
        int N = bottom.length();
        int[][] A = new int[N][N];
        int t = 0;
        for (char c : bottom.toCharArray())
            A[N - 1][t++] = c - 'A';
        return solve(A, N - 1, 0);
    }

    //A[i] - the ith row of the pyramid
    //R - integer representing the current row of the pyramid
    //N - length of current row we are calculating
    //i - index of how far in the current row we are calculating
    //Returns true iff pyramid can be built
    public boolean solve(int[][] A, int N, int i) {
        if (N == 1 && i == 1) { // If successfully placed entire pyramid
            return true;
        } else if (i == N) {
            /*if (seen.contains(R)) return false; // If we've already tried this row, give up
            seen.add(R); // Add row to cache*/
            return solve(A, N - 1, 0); // Calculate next row
        } else if (map.containsKey(A[N][i]) && map.get(A[N][i]).containsKey(A[N][i + 1])) {
            // w's jth bit is true iff block #j could be
            // a parent of A[N][i] and A[N][i+1]

            List<Integer> w = map.get(A[N][i]).get(A[N][i + 1]);//T[A[N][i]][A[N][i+1]];
            // for each set bit in w...
            for (Integer b : w) {
                A[N - 1][i] = b; //set parent to be equal to block #b
                //If rest of pyramid can be built, return true
                //R represents current row, now with ith bit set to b+1
                // in base 8.
                if (solve(A, N, i + 1)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] allowed = {"BCE", "BCF", "ABA", "CDA", "AEG", "FAG", "GGG"};
        PyramidTransitionMatrix_756 instance = new PyramidTransitionMatrix_756();
        System.out.print(instance.pyramidTransition("ABCD", Arrays.asList(allowed)));
    }
}
