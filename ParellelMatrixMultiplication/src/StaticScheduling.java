/**
 * Created by annezhao on 4/25/16.
 */
public class StaticScheduling {

    int matrixSize;
    static int[][] A;
    static int[][] B;
    static int[][] C;

    public StaticScheduling(int size) {
        matrixSize = size;
        A = MatrixGenerator.matrixInit(size);
        B = MatrixGenerator.matrixInit(size);
        C = new int[size][size];
    }

    public void displayA() {display(A);}

    public void displayB() {display(B);}

    public void displayC() {display(C);}

    private void display(int[][] matrix) {
        for (int i = 0 ; i < matrixSize ; i++ ) {
            for (int j = 0 ; j < matrixSize ; j++ )
                System.out.print(matrix[i][j]+"\t");

            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        //parameters to change for testing
        int numOfCores = 2;     //1,2,3,..., 8
        int matrixSize = 5;

        StaticScheduling staticScheduling = new StaticScheduling(matrixSize);
        Thread[] threads = new Thread[numOfCores];

        int granularity = matrixSize/numOfCores;

        if (numOfCores == 1) {
            threads[0] = new Thread(new StaticThread(staticScheduling.A, staticScheduling.B, staticScheduling.C, 0, matrixSize, staticScheduling.matrixSize));
        } else {
            //indices for dividing matrix into chunks
            int start = 0;
            int end = granularity;

            for (int i=0; i<numOfCores; i++) {
                //for last thread to take the rest of the matrix
                if (i == (numOfCores-1)) {
                    end = matrixSize;
                }
                threads[i] = new Thread(new StaticThread(staticScheduling.A, staticScheduling.B, staticScheduling.C, start, end, staticScheduling.matrixSize));
                start = end;
                end = end + granularity;

            }
        }

        for (Thread thread : threads) {
            thread.run();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Matrix A:");
        staticScheduling.displayA();
        System.out.println();

        System.out.println("Matrix B:");
        staticScheduling.displayB();
        System.out.println();

        System.out.println("Matrix C:");
        staticScheduling.displayC();
        System.out.println();

    }


}
