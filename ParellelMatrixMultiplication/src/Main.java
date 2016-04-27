import java.util.Scanner;


/**
* Created by annezhao on 4/26/16.
*/
public class Main {
public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    //parameters to change for testing
    System.out.println("Please enter number of cores (from 1 to 8): ");
    int numOfCores;     //1,2,3,..., 8
    numOfCores = keyboard.nextInt();
    while (numOfCores <1 || numOfCores > 8) {
        System.out.println(numOfCores + " is not in range from 1 to 8!");
        System.out.println("Please enter number of cores (from 1 to 8): ");
        numOfCores = keyboard.nextInt();
    }
    System.out.println("Please enter a matrix size (must be positive): ");
    int matrixSize;
    matrixSize = keyboard.nextInt();
    while (matrixSize<=0) {
        System.out.println("Matrix size must be positive!");
        System.out.println("Please enter a matrix size: ");
        matrixSize = keyboard.nextInt();
    }

    Thread[] threads = new Thread[numOfCores];

    //Static Scheduling
    StaticScheduling staticScheduling = new StaticScheduling(matrixSize);
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

    long startTime = System.nanoTime();

    for (Thread thread : threads) {
        thread.start();
    }

    for (Thread thread : threads) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    long endTime = System.nanoTime();
    //duration in milliseconds
    long duration = (endTime - startTime)/1000000;
    System.out.println("\nMatrix multiplication of size " + matrixSize + " x " + matrixSize + " using " + numOfCores + " cores");
    System.out.println("Static Scheduling: " + duration + " milliseconds");

    // System.out.println("Matrix A:");
    // staticScheduling.displayA();
    // System.out.println();
    //
    // System.out.println("Matrix B:");
    // staticScheduling.displayB();
    // System.out.println();
    //
    // System.out.println("Matrix C:");
    // staticScheduling.displayC();
    // System.out.println();

    // Dynamic Scheduling
    DynamicScheduling dynamicScheduling = new DynamicScheduling(matrixSize);

    for (int i=0; i<numOfCores; i++) {
        threads[i] = new Thread(new DynamicThread(dynamicScheduling.A, dynamicScheduling.B, dynamicScheduling.C, dynamicScheduling.matrixSize));
    }

    startTime = System.nanoTime();

    for (Thread thread : threads) {
        thread.start();
    }

    for (Thread thread : threads) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    endTime = System.nanoTime();
    //duration in milliseconds
    duration = (endTime - startTime)/1000000;
    System.out.println("\nMatrix multiplication of size " + matrixSize + " x " + matrixSize + " using " + numOfCores + " cores");
    System.out.println("Dynamic Scheduling: " + duration + " milliseconds");

    // System.out.println("Matrix A:");
    // dynamicScheduling.displayA();
    // System.out.println();
    //
    // System.out.println("Matrix B:");
    // dynamicScheduling.displayB();
    // System.out.println();
    //
    // System.out.println("Matrix C:");
    // dynamicScheduling.displayC();
    // System.out.println();
    }
}
