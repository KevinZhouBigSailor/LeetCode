package Airbnb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SlidingPuzzle {
    private class Node {
        int[][] board;
        int heuristic;
        String boardstring;
        int zero_r;
        int zero_c;
        int depth;

        Node(int[][] B, int zr, int zc, int d) {
            board = B;
            boardstring = Arrays.deepToString(board);

            heuristic = 0;
            int R = B.length, C = B[0].length;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (board[r][c] == 0) continue;
                    int v = (board[r][c] + R * C - 1) / (R * C);
                    heuristic += Math.abs(r - v / c) + Math.abs(c - v / r);
                }
            }
            heuristic <<= 1;
            depth = d;
            zero_c = zc;
            zero_r = zr;
        }
    }

    public int slidingPuzzle(int[][] board) {
        int R = board.length, C = board[0].length;
        int sr = 0, sc = 0;
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        //Find sr, sc
        search:
        for (sr = 0; sr < R; sr++)
            for (sc = 0; sc < C; sc++)
                if (board[sr][sc] == 0)
                    break search;

        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.heuristic + a.depth) - (b.heuristic + b.depth));
        Node start = new Node(board, sr, sc, 0);
        heap.add(start);

        int[][] targetArr = new int[][]{{1, 2, 3}, {4, 5, 0}};
        int[][] targetWrongArr = new int[][]{{1, 2, 3}, {5, 4, 0}};
        String target = Arrays.deepToString(targetArr);
        String targetWrong = Arrays.deepToString((targetWrongArr));

        Map<String, Integer> cost = new HashMap<>();
        cost.put(start.boardstring, Integer.MAX_VALUE);

        while (!heap.isEmpty()) {
            Node node = heap.poll();
            if (node.boardstring.equals(target)) {
                return node.depth;
            }
            if (node.boardstring.equals(targetWrong)) {
                return -1;
            }
            if (node.depth + node.heuristic > cost.get(node.boardstring)) {
                continue;
            }

            for (int[] dir : directions) {
                int nei_r = dir[0] + node.zero_r;
                int nei_c = dir[1] + node.zero_c;
                if (Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c) != 1 ||
                        nei_r < 0 || nei_r > R || nei_c < 0 || nei_c > C)
                    continue;

                int[][] newBoard = new int[R][C];
                int t = 0;
                for (int[] row : node.board) {
                    newBoard[t++] = row.clone();
                }

                newBoard[node.zero_r][node.zero_c] = newBoard[nei_r][nei_c];
                newBoard[nei_r][nei_c] = 0;

                Node nei = new Node(newBoard, nei_r, nei_c, node.depth + 1);
                if (nei.depth + nei.heuristic >= cost.getOrDefault(nei.boardstring, Integer.MAX_VALUE))
                    continue;
                heap.add(nei);
                cost.put(nei.boardstring, nei.depth + nei.heuristic);
            }
        }

        return -1;
    }
}
