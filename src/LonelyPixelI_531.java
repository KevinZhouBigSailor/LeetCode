/**
 * Created by zzhou on 1/25/2018.
 */
public class LonelyPixelI_531 {
    public int findLonelyPixel(char[][] picture) {
        int n = picture.length, m = picture[0].length;

        int[] rowCount = new int[n], colCount = new int[m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (picture[i][j] == 'B') {
                    rowCount[i]++;
                    colCount[j]++;
                }

        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1) count++;

        return count;
    }

    public static void main(String[] args) {
        LonelyPixelI_531 instance = new LonelyPixelI_531();
        char[][] testPicture = new char[][]{{'B','B',0,0,0},{'B','B',0,0,0},{0,0,0,'B','B'},{0,0,0,'B','B'}};
        instance.findLonelyPixel(testPicture);
    }
}
