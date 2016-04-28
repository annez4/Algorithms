
/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Node
{
    String Url;
    int vertex;
    int degree;
    //
    int label;
    String tuple;
    
    public Node(int vertex) {
        this.vertex = vertex;
        label = 0;
        tuple = "";
        Url="";

    }
    
    public void setUrl(String s) {
      Url=s;  
    }
}
