/**
 * Created by annezhao on 1/23/16.
 */


import java.io.*;
import java.util.*;


public class HashWithLinearProbing {
    //large prime number is usually chosen for the size of hash table.
    int tableSize = 423697;
    Hash[] hash;

    int spaceOccupied;
    ArrayList<Integer> firstInChain = new ArrayList<Integer>(); //to store every first key in line

    // constructor
    public HashWithLinearProbing() {
        hash = new Hash[tableSize];

        for(int i = 0 ; i < tableSize ; i++ ){
            hash[i] = new Hash();
        }
    }

    //Hash Function that uses hashCode() method
    public int hashVal(String p) {
        return (Math.abs(p.hashCode()) % tableSize);
    }


    // add() method will implement a hash table using the hashVal() method described above.
    // This improved hashing method will use the "next" field to chain all the keys that have collisions.
    // To store a key S, this method will start at the beginning of the linked list at k = hashVal(S),
    // and navigate the linked list by checking if S is not in the table already.  If S is not in the table, then it stores it
    // at some linearly probed location starting after a table index j whose next field is -1.  As you are aware, this (-1)
    // signifies the end of the linked list.  Then you start linear probing for empty locations.  Do not start linear probing after
    // the first collision at k, necessarily, unless Hash[k].next=-1
    public void add(String str) {
        int key = hashVal(str);

        while (hash[key].next != -1){  // to the end of the chain
            if (hash[key].val.equals(str)) {  //eliminate duplicates
                return;
            }
            key = hash[key].next;
        }

        if (hash[key].val.equals(str)) {  //for the last key in the chain, eliminate duplicates
            return;
        }

        int lastKey = key;  // to keep track of the current last key in the chain

        while (!hash[key].val.isEmpty()) { //occupied, go to the first empty spot
            if (key == tableSize - 1) {
                key = 0;  // back to the beginning of the table
            } else {
                key ++ ;
            }
        }

        if (lastKey != key){
            hash[lastKey].next = key;   // if not the first in the chain
        }

        hash[key].val = str;

        if (key == hashVal(str)) { // check if this is a new start for a chain, if so, add to array
            firstInChain.add(key);
        }
        spaceOccupied ++ ;
    }


    // returns true if the hash table contains string p otherwise returns false.
    public boolean contains(String p) {
        int key = hashVal(p);
        boolean b = false;

        do {
            if (hash[key].val.equals(p)) {
                b = !b;
            } else {
                key = hash[key].next;
            }
        } while ((key != -1) && !b);  //not the end nor duplicate found
        return b;
    }


    //returns true if found and removes the String p, else returns false.
    public boolean remove(String p) {
        int key = hashVal(p);
        int prevKey;
        int nextKey;
        boolean b = true;

        while(!hash[key].val.equals(p)) {
            key = hash[key].next;

            if (key == -1) {  //at the end of the chain, no match
                return !b;
            }
        }

        while (hash[key].next != -1){
            nextKey = hash[key].next;
            hash[key].val = hash[nextKey].val;
            prevKey = key;
            key = nextKey;

            if (key == -1) {
                hash[prevKey].next = -1;  // last of the chain, set previous key to -1
            }
        }
        hash[key].val = "";  // delete the last value in chain

        return b;
    }


    // uses EnglishWordList file to store all the words in the hashTable.
    // You cannot eliminate the duplicates in the file by some pre-processing.  The method add()
    // should be written such that duplicates are not stored in the hash table.
    public void readData( ) {
        Scanner reader = null;

        try {
            reader = new Scanner(new File("EnglishWordList.txt"));
        }catch (FileNotFoundException ex) {
            System.out.println(ex + "  file not found ");
        }

        while (reader.hasNext()) {
            String str = reader.nextLine();
            add(str);
        }
    }

    public static void main(String[] args){
        HashWithLinearProbing hlp = new HashWithLinearProbing();

        hlp.readData();
        hlp.printStat();
    }

    // this function is for printing the stats.
    public void printStat(){
        // calculate the occupied location
        System.out.println(spaceOccupied); // occupied location 250154

        // calculate the load factor
        double loadFactor = (double) spaceOccupied / (double) tableSize; // loadFactor = 0.5904077678152075
        System.out.println(loadFactor);

        // calculate the number of linked lists
        int numOfList = firstInChain.size();
        System.out.println(numOfList);  // number of linked lists 176031

        // calculate the max search factor
        int maxLengthOfChain = 1;

        for (Integer integer: firstInChain) {
            int lengthOfChain = 1;

            while (hash[integer].next != -1) {
                lengthOfChain ++ ;
                integer = hash[integer].next;
            }

            if (maxLengthOfChain < lengthOfChain) {
                maxLengthOfChain = lengthOfChain;
            }
        }
        System.out.println(maxLengthOfChain);

        // calculate the sum of lengths of linked lists
        int sumLengthOfChain = 0;

        for (int x = 0 ; x < tableSize; x ++) {
            int index = x ;
            if(hash[index].val != "") {
                int length = 1;
                while (hash[index].next != -1) {
                    length++;
                    index = hash[index].next;
                }
                sumLengthOfChain = sumLengthOfChain + length; // sum of lengths of linked list 365769
            }
        }
        System.out.println(sumLengthOfChain);

        //calculate the average search factor
        double aveSearchFactor = (double) sumLengthOfChain / (double) spaceOccupied;
        System.out.println(aveSearchFactor);  //average search factor 1.4621753000151907
    }
}




