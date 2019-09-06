package WeWork;

public class Minesweeper {
    int[] dr = {-1, 1, 0, 0, -1, 1, 1, -1};
    int[] dc = {0, 0, 1, -1, -1, -1, 1, 1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];

        if (board[row][col] == 'M') {
            board[row][col] = 'X';
        } else {
            int count = 0;
            for (int di = 0; di < 8; di++) {
                int r = row + dr[di];
                int c = col + dc[di];
                if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                if (board[r][c] == 'M' || board[r][c] == 'X') count++;
            }

            if (count > 0) {
                board[row][col] = (char) (count + '0');
            } else {
                board[row][col] = 'B';
                for (int di = 0; di < 8; di++) {
                    int r = row + dr[di];
                    int c = col + dc[di];
                    if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                    if (board[r][c] == 'E') updateBoard(board, new int[]{r, c});
                }
            }
        }
        return board;
    }
}
