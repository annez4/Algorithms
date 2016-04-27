/**
 * Created by Ilona on 4/25/2016.
 */
public class StaticThread implements Runnable {
    int[][] A;
    int[][] B;
    int[][] C;
    int start;
    int end;
    int size;

    public StaticThread(int[][] A, int[][] B, int[][] C, int start, int end, int size) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.start = start;
        this.end = end;
        this.size = size;
    }

    @Override
    public void run() {
        for (int i=start; i<end; i++) {
            for (int j=0; j<size; j++) {
                for (int k=0; k<size; k++) {
                    C[i][j] = C[i][j] + (A[i][k]*B[k][j]);
                }
            }
        }
    }
}
