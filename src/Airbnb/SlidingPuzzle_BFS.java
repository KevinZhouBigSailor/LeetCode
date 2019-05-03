package Airbnb;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

public class SlidingPuzzle_BFS {
    private class Node {
        int[][] board;
        int zero_r;
        int zero_c;
        String boardString;
        int depth;

        Node(int[][] B, int r, int c, int d) {
            board = B;
            boardString = Arrays.deepToString(board);
            zero_r = r;
            zero_c = c;
            depth = d;
        }
    }

    public int slidingPuzzle(int[][] board) {
        int R = board.length, C = board[0].length;
        int sr = 0, sc = 0;
        search:
        for (sr = 0; sr < R; sr++) {
            for (sc = 0; sc < C; sc++) {
                if (board[sr][sc] == 0)
                    break search;
            }
        }
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<Node> queue = new ArrayDeque<>();
        Node start = new Node(board, sr, sc, 0);
        queue.offer(start);
        HashSet<String> seen = new HashSet<>();

        String target = Arrays.deepToString(new int[][]{{1, 2, 3}, {4, 5, 0}});
        String targetWrong = Arrays.deepToString(new int[][]{{1, 2, 3}, {5, 4, 0}});

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.boardString.equals(target)) {
                return node.depth;
            }
            if (node.boardString.equals(targetWrong)) {
                return -1;
            }

            for (int[] dir : directions) {
                int nei_r = dir[0] + node.zero_r;
                int nei_c = dir[1] + node.zero_c;

                if ((Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c)) != 1
                        || nei_r < 0 || nei_r >= R || nei_c < 0 || nei_c >= C) {
                    continue;
                }

                int[][] newBoard = new int[R][C];
                int t = 0;
                for (int[] row : node.board) {
                    newBoard[t++] = row.clone();
                }
                newBoard[node.zero_r][node.zero_c] = newBoard[nei_r][nei_c];
                newBoard[nei_r][nei_c] = 0;
                Node nei = new Node(newBoard, nei_r, nei_c, node.depth + 1);
                if (seen.contains(nei.boardString)) {
                    continue;
                }
                queue.offer(nei);
                seen.add(nei.boardString);
            }
        }

        return -1;
    }
}
