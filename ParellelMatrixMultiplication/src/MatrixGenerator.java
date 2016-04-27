import java.util.Random;

/**
 * Created by annezhao on 4/25/16.
 */

public class MatrixGenerator {


    public static int[][] matrixInit(int n) {
        int[][] matrix = new int[n][n];
        Random r = new Random();
        for(int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                matrix[i][j] = r.nextInt(5);
            }
        }
        return matrix;
    }
}
