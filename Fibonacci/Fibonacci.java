/**
 * Created by annezhao on 2/7/16.
 */

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.ListIterator;


public class Fibonacci {

    public String iterativeMethod(long k) {
        if (k <= 1){
            return Long.toString(k);
        }

        BigInteger prev = new BigInteger("0");
        BigInteger curr = new BigInteger("1");
        BigInteger next;

        for (long i = 1; i < k; i ++){
            next = curr.add(prev);
            prev = curr;
            curr = next;
        }

        return curr.toString();
    }



    public String cleverMatrix(long k) {
        if (k <= 1) {
            return Long.toString(k);
        }

        BigInteger[][] A = {{BigInteger.ZERO, BigInteger.ONE},{BigInteger.ONE, BigInteger.ONE}};
        BigInteger[][] F = {{BigInteger.ZERO},{BigInteger.ONE}};

        String temp = Long.toBinaryString(k);
        String s = new StringBuilder(temp).reverse().toString();    //reverse to make small index comes first

        LinkedList powers = new LinkedList();

        for (int i = 0; i < s.length(); i++) {      // retrieve the index for the 1s
            if (s.charAt(i) == '1') {
                powers.add((long) Math.pow(2, i));
            }
        }

        LinkedList<BigInteger[][]> listOfA = new LinkedList<BigInteger[][]>();

        long powerMax = (Long) powers.peekLast();
        ListIterator iterator = powers.listIterator();
        long power = (Long) iterator.next();

        for (long p = 1; p <= powerMax ; p *= 2) {
            BigInteger matrix00 = new BigInteger(A[0][0].toString());   // to pass value instead of reference
            BigInteger matrix01 = new BigInteger(A[0][1].toString());
            BigInteger matrix10 = new BigInteger(A[1][0].toString());
            BigInteger matrix11 = new BigInteger(A[1][1].toString());

            if(p == power) {
                //matrix has the same value as A does
                BigInteger[][] matrix = {{matrix00, matrix01}, {matrix10, matrix11}};
                listOfA.add(matrix);    // save the valid matrix A, which is the powers of A in the list

                if(iterator.hasNext()) {
                    power = (Long)iterator.next();   // retrieve the next valid power of A
                }
            }

            // Matrix A multiplies itself
            A[0][0] = matrix00.multiply(matrix00).add(matrix01.multiply(matrix10));
            A[0][1] = matrix00.multiply(matrix01).add(matrix01.multiply(matrix11));
            A[1][0] = matrix10.multiply(matrix00).add(matrix11.multiply(matrix10));
            A[1][1] = matrix10.multiply(matrix01).add(matrix11.multiply(matrix11));
        }

        for (BigInteger[][] validA : listOfA) {   // multiply F with valid powers of matrix A,
            BigInteger temp1 = F[0][0];
            F[0][0] = validA[0][0].multiply(temp1).add(validA[0][1].multiply(F[1][0]));
            F[1][0] = validA[1][0].multiply(temp1).add(validA[1][1].multiply(F[1][0]));
        }

        return F[0][0].toString();
    }


    public static void main(String[] args){
        Fibonacci f = new Fibonacci();

        System.out.println(f.iterativeMethod(10));
        System.out.println(f.cleverMatrix(10));


        long a = System.currentTimeMillis();
        System.out.println(f.iterativeMethod(10000));
        long b = System.currentTimeMillis();
        System.out.println(b-a);
        a = System.currentTimeMillis();
        System.out.println(f.cleverMatrix(10000));
        b = System.currentTimeMillis();
        System.out.println(b-a);


        a = System.currentTimeMillis();
        System.out.println(f.iterativeMethod(50000));
        b = System.currentTimeMillis();
        System.out.println(b-a);
        a = System.currentTimeMillis();
        System.out.println(f.cleverMatrix(50000));
        b = System.currentTimeMillis();
        System.out.println(b-a);

        a = System.currentTimeMillis();
        System.out.println(f.iterativeMethod(100000));
        b = System.currentTimeMillis();
        System.out.println(b-a);
        a = System.currentTimeMillis();
        System.out.println(f.cleverMatrix(100000));
        b = System.currentTimeMillis();
        System.out.println(b-a);

        a = System.currentTimeMillis();
        System.out.println(f.iterativeMethod(500000));
        b = System.currentTimeMillis();
        System.out.println(b-a);
        a = System.currentTimeMillis();
        System.out.println(f.cleverMatrix(500000));
        b = System.currentTimeMillis();
        System.out.println(b-a);

        a = System.currentTimeMillis();
        System.out.println(f.iterativeMethod(1000000));
        b = System.currentTimeMillis();
        System.out.println(b-a);
        a = System.currentTimeMillis();
        System.out.println(f.cleverMatrix(1000000));
        b = System.currentTimeMillis();
        System.out.println(b-a);

        a = System.currentTimeMillis();
        System.out.println(f.iterativeMethod(2000000));
        b = System.currentTimeMillis();
        System.out.println(b-a);
        a = System.currentTimeMillis();
        System.out.println(f.cleverMatrix(2000000));
        b = System.currentTimeMillis();
        System.out.println(b-a);
    }
}
