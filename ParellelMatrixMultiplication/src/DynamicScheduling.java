/**
 * Created by annezhao on 4/25/16.
 */
public class DynamicScheduling {
    int matrixSize;
    static int[][] A;
    static int[][] B;
    static int[][] C;

    public DynamicScheduling(int size) {
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

        DynamicScheduling dynamicScheduling = new DynamicScheduling(matrixSize);
        Thread[] threads = new Thread[numOfCores];

        for (int i=0; i<numOfCores; i++) {
            threads[i] = new Thread(new DynamicThread(dynamicScheduling.A, dynamicScheduling.B, dynamicScheduling.C, dynamicScheduling.matrixSize));
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
        dynamicScheduling.displayA();
        System.out.println();

        System.out.println("Matrix B:");
        dynamicScheduling.displayB();
        System.out.println();

        System.out.println("Matrix C:");
        dynamicScheduling.displayC();
        System.out.println();

    }
}
