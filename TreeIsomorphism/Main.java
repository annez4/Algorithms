import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //TreeGen tree = new TreeGen(10);

        ArrayList<String> tree1 = new  ArrayList();
        ArrayList<String> tree2 = new  ArrayList();;
        ArrayList<String> tree3 = new  ArrayList();;
        ArrayList<String> tree4 = new  ArrayList();;

        try {
            File file1 = new File("Tree1.txt");
            File file2 = new File("Tree2.txt");
            File file3 = new File("Tree3.txt");
            File file4 = new File("Tree4.txt");
            tree1 = getTree(file1);
            tree2 = getTree(file2);
            tree3 = getTree(file3);
            tree4 = getTree(file4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // a new TreeGen should be created before each comparison
        // limitation: data for comparison is built into Node structure
        TreeGen T1 = new TreeGen(tree1);
        TreeGen T2 = new TreeGen(tree2);
        TreeGen T3 = new TreeGen(tree3);
        TreeGen T4 = new TreeGen(tree4);

        System.out.print("\nTest T1 and T2 for isomorphism:    ");
        boolean T1vsT2 = Isomorphism.isomorphism(T1, T2);
        System.out.println(T1vsT2);

        T1 = new TreeGen(tree1);
        System.out.print("\nTest T1 and T3 for isomorphism:    ");
        boolean T1vsT3 = Isomorphism.isomorphism(T1, T3);
        System.out.println(T1vsT3);

        T1 = new TreeGen(tree1);
        System.out.print("\nTest T1 and T4 for isomorphism:    ");
        boolean T1vsT4 = Isomorphism.isomorphism(T1, T4);
        System.out.println(T1vsT4);

        T2 = new TreeGen(tree2);
        T3 = new TreeGen(tree3);
        System.out.print("\nTest T2 and T3 for isomorphism:    ");
        boolean T2vsT3 = Isomorphism.isomorphism(T2, T3);
        System.out.println(T2vsT3);

        T2 = new TreeGen(tree2);
        T4 = new TreeGen(tree4);
        System.out.print("\nTest T2 and T4 for isomorphism:    ");
        boolean T2vsT4 = Isomorphism.isomorphism(T2, T4);
        System.out.println(T2vsT4);

        T3 = new TreeGen(tree3);
        T4 = new TreeGen(tree4);
        System.out.print("\nTest T3 and T4 for isomorphism:    ");
        boolean T3vsT4 = Isomorphism.isomorphism(T3, T4);
        System.out.println(T3vsT4);
    }

    /* read files */
    public static ArrayList<String> getTree(File file) throws Exception {
        ArrayList<String> tree = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            tree.add(line);
        }
        br.close();

        return tree;
    }
}
