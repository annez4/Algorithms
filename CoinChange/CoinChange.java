import java.util.ArrayList;
import java.util.List;

public class CoinChange {
    public void findMaxCoverage(int[] D, int T) {
        boolean hasOne = false;

        for (int i = 0; i < D.length; i ++) {
            if (D[i] == 1) {
            	i = D.length;
            	hasOne = true;
            }
        }

        if (!hasOne) {
        	System.out.println("Maximum coverage is 0.");
        	System.exit(0);
        }


        List<Integer> coins = new ArrayList<Integer>();

        coins.add(0);
        int max = 0;

        boolean exceed = false;

        while(!exceed) {
        	coins.add(Integer.MAX_VALUE);

        	for (int i = 0; i < D.length; i ++) {
        		if (D[i] <= max) {
        			int temp = coins.get(max - D[i]) + 1;

        			if(temp < coins.get(max)) {
        				coins.set(max, temp);
        			}
        		}
        	}

        	if(coins.get(max) > T) {
        		exceed = true;
        	}

            max ++;
        }

        System.out.println("Maximum coverage is: " + (max - 2));
    }


    public static void main(String[] args) {
    	CoinChange cc = new CoinChange();

    	int[] D = {1, 5, 7, 13};
    	int T = 500;

    	cc.findMaxCoverage(D, T);
    }
}
