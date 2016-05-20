import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class Jumble {
	public static void main(String[] args) throws Exception {
		boolean found = false;
		
		Dictionary d = new Dictionary();
		Permutation p1 = new Permutation();
		Permutation p2 = new Permutation();
		
		Hashtable<Integer, ArrayList<String>> dictionary = d.getSortedList();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please insert a word: ");
		String word = sc.nextLine();
		System.out.println("Please insert the length of subString1: ");
		int part1 = sc.nextInt();
		System.out.println("Please insert the length of subString2: ");
		int part2 = sc.nextInt();
		
		sc.close();
		
		// permutation of part1
		p1.permutation("", word);
		
		HashSet<String> permutation1 = p1.getPermutation(part1);
		ArrayList<String> dictionaryLengthPart1 = dictionary.get(part1);
		ArrayList<String> p1Matches = new ArrayList<String>();
		
		int size1 = dictionaryLengthPart1.size();
		
		Iterator<String> iterator1 = permutation1.iterator();
		while (iterator1.hasNext()) {
			String temp = iterator1.next();
			
			for (int i = 0; i < size1; i ++) {
				if (temp.equals(dictionaryLengthPart1.get(i))) {
					p1Matches.add(temp);
				}
			}
		}
		
		int p1MatchNumber = p1Matches.size();
		if (p1MatchNumber == 0) {	// no match
			System.out.println(found);
			System.exit(0);
		}
		
		for (int i = 0; i < p1MatchNumber; i ++) {
			String thisMatch = p1Matches.get(i);
			StringBuilder sb = new StringBuilder(word);
			
			ArrayList<String> combinations = new ArrayList<String>();
			combinations.add(" ");
			boolean sameSubWord = false;
			
			for(int j = 0; j < part1; j ++) {
				char ch = thisMatch.charAt(j);
				String temp = sb.toString();
				int index = temp.indexOf(ch);
				
				sb.deleteCharAt(index);
			}
			
			String subWord = sb.toString();
			
			for (String str : combinations) {
				if(str.equals(subWord)) {
					sameSubWord = true;
					break;
				}
			}
			
			if (sameSubWord) {
				continue;
			} else {
				combinations.add(subWord);
			}
			
			
			p2.permutation("", subWord);
			
			HashSet<String> permutation2 = p2.getPermutation(part2);
			ArrayList<String> dictionaryLengthPart2 = dictionary.get(part2);
			
			int size2 = dictionaryLengthPart2.size();
			
			Iterator<String> iterator2 = permutation2.iterator();
			while (iterator2.hasNext()) {
				String temp = iterator2.next();
				
				for (int k = 0; k < size2; k ++) {
					if (temp.equals(dictionaryLengthPart2.get(k))) {
						found = true;
						break;
					}
				}
				
				if (found) {
					System.out.println(found);
					
					System.out.println("p1 = " + thisMatch + " p2 = " + temp);
					System.exit(0);
				}
			}
		}
		
		System.out.println("false");
	}
}
