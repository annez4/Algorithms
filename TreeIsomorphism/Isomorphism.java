import java.util.ArrayList;

/**
 * Created by Ilona on 4/12/2016.
 */
public class Isomorphism {
    public static boolean isomorphism(TreeGen t1, TreeGen t2) {

        int height = t1.getHeight();
        int height2 = t2.getHeight();

        if (height != height2) {
            return false;
        }

        //last level nodes
        ArrayList<Node> l1 = t1.nodeListByLevel(height);
        ArrayList<Node> l2 = t2.nodeListByLevel(height);

        if (l1.size() != l2.size()) {
            return false;
        }

        for (int i=0; i<l1.size(); i++) {
            l1.get(i).tuple = "0";
            l2.get(i).tuple = "0";
        }

        ArrayList<Node> currentL1;
        ArrayList<Node> currentL2;

        for (int i=height-1; i>=0; i--) {
            currentL1 = t1.nodeListByLevel(i);
            currentL2 = t2.nodeListByLevel(i);

            if (currentL1.size() != currentL2.size()) {
                return false;
            }


            for (int j = 0; j < l1.size(); j ++) {
                Node parent1 = t1.getParent(l1.get(j).vertex);
                Node parent2 = t2.getParent(l2.get(j).vertex);
                parent1.tuple += (l1.get(j).label + ",");
                parent2.tuple += (l2.get(j).label + ",");
            }

            // for (Node n : l1) {
            //     Node parent = t1.getParent(n.vertex);
            //     parent.tuple += (n.label + ",");
            // }
            //
            // for (Node n : l2) {
            //     Node parent = t2.getParent(n.vertex);
            //     parent.tuple += (n.label + ",");
            // }

            ArrayList<String> s1 = new ArrayList();
            ArrayList<String> s2 = new ArrayList();

            for (Node n : currentL1) {
                String tuple = n.tuple;
                if (tuple.length() == 0) {
                    s1.add("0");
                } else {
                    s1.add(tuple);
                }
            }
        }

        return false;
    }
}
