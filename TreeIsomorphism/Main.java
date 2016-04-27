import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //TreeGen tree = new TreeGen(10);


        try {
            File file1 = new File("Tree/Tree1.txt");
            ArrayList<String> demo = getTree(file1, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("\n---Test of constructor---");
        ArrayList<String> someTree = new ArrayList();
        someTree.add("0,1");
        someTree.add("0,2");
        someTree.add("0,4");
        someTree.add("1,5");
        someTree.add("1,8");
        someTree.add("4,6");
        someTree.add("6,7");

        TreeGen tree = new TreeGen(someTree);

        System.out.println("\nChild-Parent Map: ");
        tree.displayChildParent();
        System.out.println("\nParent-Children Map: ");
        tree.displayParentChildren();
        System.out.println("\nLevel-Nodes Map: ");
        tree.displayLevelNodeMap();

        System.out.println("\nThe height of the tree is: " + tree.getHeight() + "\n");

        // if your file has different name, just put it under the root folder and change the name here
//        File file = new File("URLs.txt");
//        try {
//            tree.addUrlToNodes(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("\n---Test of getChildren() method---");
        tree.getChildren(-1);
        tree.getChildren(0);
        tree.getChildren(4);
        tree.getChildren(10);

        System.out.println("\n---Test of getParent() method---");
        tree.getParent(-1);
        tree.getParent(0);
        tree.getParent(1);
        tree.getParent(4);
        tree.getParent(10);

        System.out.println("\n---Test of nodeListByLevel() method---");
        tree.nodeListByLevel(-1);
        tree.nodeListByLevel(0);
        tree.nodeListByLevel(1);
        tree.nodeListByLevel(5);








    }

    /* read files */
    public static ArrayList<String> getTree(File file, int n) throws Exception {
        ArrayList<String> tree = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (int i = 0; i < n; i ++) {
            tree.add(br.readLine());

            System.out.println(tree.get(i));
        }
        br.close();

        return tree;
    }
}
