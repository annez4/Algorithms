import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class Dictionary {
	ArrayList<String> dictionary = new ArrayList<String>();
	Hashtable<Integer, ArrayList<String>> sortedList = new Hashtable<Integer, ArrayList<String>>();
	
	public Dictionary() throws Exception {
		File file = new File("dictionary.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		
		while ((line = br.readLine()) != null) {
//			dictionary.add(line);	
			Integer key = line.length();
			
			if (!sortedList.containsKey(key)) {
				sortedList.put(key, new ArrayList<String>());
			}

			sortedList.get(key).add(line);
		}
		
		br.close();
	}
	
	public Hashtable<Integer, ArrayList<String>> getSortedList() {
		return sortedList;
	}
}
