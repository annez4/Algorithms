import java.math.BigInteger;

public class BinomialCoefficient {
	// Pascal's Triangle
	public void pascalTriangle(int n) {
		if (n <= 0) {
			System.exit(0);
		}

		BigInteger[] arr1 = new BigInteger[n + 1];
		arr1[0] = BigInteger.ONE;

		for (int i = 1; i < n; i ++) {
			// a temporary array to store the values of the next row.
			BigInteger[] arr2 = new BigInteger[n + 1];
			arr2[0] = BigInteger.ONE;
			arr2[1] = BigInteger.ONE;

			for (int j = 1 ; j < i; j ++) {
				arr2[j] = arr1[j-1].add(arr1[j]);
			}

			arr2[i] = BigInteger.ONE;

			// copy the value to arr1
			for(int m = 0; m < n; m ++) {
				arr1[m] = arr2[m];
			}

			arr2 = null;	// release the memory space of arr2
		}

		for(int m = 0; m < n; m ++) {
			System.out.println(arr1[m]);
		}
	}

	public static void main(String[] args) {
		BinomialCoefficient bc = new BinomialCoefficient();
		bc.pascalTriangle(6);
	}
}
