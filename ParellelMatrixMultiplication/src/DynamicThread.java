/**
 * Created by Ilona on 4/25/2016.
 */
public class DynamicThread implements Runnable {
    int[][] A;
    int[][] B;
    int[][] C;
    static int row;
    int size;
    private static Object lock = new Object();

    public DynamicThread(int[][] A, int[][] B, int[][] C, int size) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.size = size;
    }

    @Override
    public void run() {
        int i;
        while (row < size) {
            synchronized (lock) {
                i = row;
                row++;
            }
            for (int j=0; j<size; j++) {
                for (int k=0; k<size; k++) {
                    C[i][j] = C[i][j] + (A[i][k]*B[k][j]);
                }
            }
        }
    }
}
