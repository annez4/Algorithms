
/**
 * Write a description of class Hash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hash {
    String val;
    int next;
    
    public Hash() {
        val = "";
        next = -1;       
    }
    
    public Hash(String p, int k) {
        val = p;
        next = k;
    }
}
