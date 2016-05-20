import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class Permutation {
	ArrayList<String> p = new ArrayList<String>();
	
	public void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0) {
	    	p.add(prefix);
	    }
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
	
	public HashSet<String> getPermutation(int length) {
		HashSet<String> p_Sub = new HashSet<String>();
		Iterator<String> iterator = p.iterator();
		    
		while (iterator.hasNext()) {
			p_Sub.add(iterator.next().substring(0, length));
		}
		
		return p_Sub;
	}
}
