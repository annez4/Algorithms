

/**
 * Write a description of class HashWithLinearProbing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;
import java.math.*;


public class HashWithLP
 {

   //large prime number is usually chosen for the size of hash table.
    int tableSize =423697;
  
    Hash [] hash;
   
    
 // constructor   
public HashWithLP() {
        
    hash = new Hash[tableSize];
    for (int i=0;i<tableSize;i++)
    hash[i]=new Hash();
  
        
        
    }
    
	//Hash Function that uses hashCode() method
    public int hashVal(String p) {
        
        return (Math.abs(p.hashCode())%tableSize);
        
        
        
    }
    
   
   
   
  
  // add() method will implement a hash table using the hashVal() method described above.
// This improved hashing method will use the "next" field to chain all the keys that have collisions.  
// To store a key S, this method will  start at the beginning of the linked list at k=hashVal(S), 
// and navigate the linked list by checking if S is not in the table already.  If S is not in the table, then it stores it 
// at some linearly probed location starting after a table index j whose next field is =-1.  As you are aware, this (-1)
// signifies the end of the linked list.  Then you start linear probing for empty locations.  Do not start linear probing after 
// the first collion at k, necessarily, unless Hash[k].next=-1
    
    
    public void add(String str)   {
    
  
       
        
        
     
}

// returns true if the hashtable contains string p otherwise returns false.


public boolean contains(String p) {
    
  
    
    
}

    
   
   //returns true if found and removes the String p, else returns false.
    
    public boolean remove(String p) {
        
   
    
}
    
    
    
    
    
    
	// uses EnglishWordList file to store all the words in the hashTable.
	// You cannot eliminate the duplicates in the file by some pre-processing.  The method add() 
	// should be written such that duplicates are not stored in the hash table.


public void readData( )  {
	
    HashWithLP hlp= new HashWithLP();
	Scanner reader=null;
        try { 
            reader = new Scanner(new File("EnglishWordList.txt"));
           }
            catch ( FileNotFoundException ex){
              System.out.println(ex+"  file not found ");
            }
    while (reader.hasNext()){
        String str = reader.nextLine();
        hlp.add(str);
        

}


}
}
