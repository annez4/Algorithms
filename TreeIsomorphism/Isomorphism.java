import java.util.*;

/**
 * Created by Ilona on 4/12/2016.
 */
public class Isomorphism {
    public static boolean isomorphism(TreeGen t1, TreeGen t2) {

        int height = t1.getHeight();
        int height2 = t2.getHeight();

        if (height != height2) {
            //System.out.println("TREES HAVE DIFFERENT HEIGHTS!");
            return false;
        }

        //last level nodes
        ArrayList<Node> l1 = t1.nodeListByLevel(height);
        ArrayList<Node> l2 = t2.nodeListByLevel(height);

        if (l1.size() != l2.size()) {
            //System.out.println("INITIAL L1.SIZE AND L2.SIZE ARE DIFFERENT");
            return false;
        }

        //all nodes on the last level are leaves
        for (int i=0; i<l1.size(); i++) {
            l1.get(i).tuple = "0";
            l2.get(i).tuple = "0";
        }

        ArrayList<Node> currentL1;
        ArrayList<Node> currentL2;

        //for each level
        for (int i=height-1; i>=0; i--) {
            currentL1 = t1.nodeListByLevel(i);
            currentL2 = t2.nodeListByLevel(i);

            if (currentL1.size() != currentL2.size()) {
                //System.out.println("L1.SIZE AND L2.SIZE ARE DIFFERENT ON LEVEL" + i);
                return false;
            }

            //copy children labels to parents' tuple
            for (Node n : l1) {
                Node parent = t1.getParent(n.vertex);
                parent.tuple += (n.label + ",");
            }

            for (Node n : l2) {
                Node parent = t2.getParent(n.vertex);
                parent.tuple += (n.label + ",");
            }

            //sequences of tuple for comparison
            ArrayList<String> s1 = new ArrayList();
            ArrayList<String> s2 = new ArrayList();

            //add tuple to sequences
            for (Node n : currentL1) {
                String tuple = n.tuple;
                if (tuple.length() == 0) {
                    s1.add("0");
                } else {
                    n.tuple = RadixBucketSort.sortString(tuple);
                    s1.add(n.tuple);
                }
            }

            for (Node n : currentL2) {
                String tuple = n.tuple;
                if (tuple.length() == 0) {
                    s2.add("0");
                } else {
                    n.tuple = RadixBucketSort.sortString(tuple);
                    s2.add(n.tuple);
                }
            }

            Collections.sort(s1);
            Collections.sort(s2);

            //compare sequences of tuple
            for (int j = 0; j < currentL1.size(); j ++) {
                if (!s1.get(j).equals(s2.get(j))) {
                    //System.out.println("ELEMENT " + s1.get(j)+ " IS NOT EQUAL " + s2.get(j) + " ON LEVEL " + i + " j=" + j);
                    return false;
                }
            }

            //create list with unique tuple only
            Set<String> hs = new HashSet();
            hs.addAll(s1);
            List<String> uniqueTuple = new ArrayList();
            uniqueTuple.addAll(hs);
            Collections.sort(uniqueTuple);

            //assign labels to nodes on the current level
            for (Node n : currentL1) {
                String tuple = n.tuple;
                if (t1.getChildren(n.vertex).isEmpty()) {
                    n.label = 0;
                }
                for (int k=0; k<uniqueTuple.size(); k++) {
                    if (tuple.equals(uniqueTuple.get(k))) {
                        n.label = k+1;
                    }
                }
            }

            for (Node n : currentL2) {
                String tuple = n.tuple;
                if (t2.getChildren(n.vertex).isEmpty()) {
                    n.label = 0;
                }
                for (int k=0; k<uniqueTuple.size(); k++) {
                    if (tuple.equals(uniqueTuple.get(k))) n.label = k+1;
                }
            }

            l1 = currentL1;
            l2 = currentL2;

        }

        //compare roots
        if (t1.getNode(0).label == t2.getNode(0).label){
            return true;
        } else {
            System.out.println("ROOTS LABELS ARE NOT EQUAL");
            return false;
        }
    }

    /* simple test of isomorphism method */
    public  static  void main(String[] args) {
        ArrayList<String> someTree = new ArrayList();
        someTree.add("0,1");
        someTree.add("0,2");
        someTree.add("0,4");
        someTree.add("1,5");
        someTree.add("1,8");
        someTree.add("2,3");
        someTree.add("4,6");
        someTree.add("4,9");
        someTree.add("6,7");

        Collections.sort(someTree);

        TreeGen tr1 = new TreeGen(someTree);
        TreeGen tr2 = new TreeGen(someTree);

        System.out.println("\nChild-Parent Map: ");
        tr1.displayChildParent();
        tr2.displayChildParent();
        System.out.println("\nParent-Children Map: ");
        tr1.displayParentChildren();
        tr2.displayParentChildren();
        System.out.println("\nLevel-Nodes Map: ");
        tr1.displayLevelNodeMap();
        tr2.displayLevelNodeMap();

        System.out.println("\nThe height of the tree is: " + tr1.getHeight() + "\n");
        System.out.println("\nThe height of the tree is: " + tr2.getHeight() + "\n");


        System.out.println("---Initial test---");
        System.out.print("\nTest tree and tree for isomorphism:    ");
        boolean itself = Isomorphism.isomorphism(tr1, tr2);
        System.out.println(itself);

        System.out.println("---Checking labels after isomorphism test was performed---");

        for (int i=0; i<tr1.n; i++) {
            System.out.println("Node " + i + " has label " + tr1.getNode(i).label);
        }
    }

}
