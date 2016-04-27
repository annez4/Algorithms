import java.io.*;
import java.util.*;
public class TreeGen {

    int n; // number of vertices
    ArrayList<String> tree; // list of edges
    Node[] treeNodes;
    int height;

    HashMap<Integer, LinkedList<Integer>> parentChildrenMap = new HashMap();
    HashMap<Integer, Integer> childParentMap = new HashMap();
    HashMap<Integer, LinkedList<Integer>> levelNodeMap = new HashMap();

    /* Constructors */
    public TreeGen(int k) {
        n = k;
        tree = new ArrayList<String>(n-1);
        treeNodes = new Node[n];
        //initialize array of Nodes
        for (int i=0; i<n; i++) {
            treeNodes[i] = new Node(i);
            //treeNodes[i].vertex = i;
        }
        rootedTree();
        constructParentChildrenRelations();
        constructLevelNodeMap();
    }

    public TreeGen(ArrayList<String> tree) {
        this.tree = tree;
        n = tree.size() + 1;
        treeNodes = new Node[n];
        //initialize array of Nodes
        for (int i=0; i<n; i++) {
            treeNodes[i] = new Node(i);
            //treeNodes[i].vertex = i;
        }
        constructParentChildrenRelations();
        constructLevelNodeMap();
    }

    // root is numbered 0.
    // generates edge pairs that form a tree
    private void rootedTree() {
        Random p = new Random();
        System.out.println("0,1");
        tree.add("0,1");
        for (int j = 2; j < n; j ++) {
            int r = p.nextInt(j);
            System.out.println(r + "," + j);
            tree.add(r + "," + j);
        }
    }

    /* Constructs two maps:
     * One contains Child-Parent information;
     * Second contains Parent-Children information*/
    private void constructParentChildrenRelations() {
        parentChildrenMap.put(0, new LinkedList<Integer>());
        for (String edge : tree) {
            String[] currentEdge = edge.split(",");
            int parent = Integer.parseInt(currentEdge[0]);
            int child = Integer.parseInt(currentEdge[1]);
            childParentMap.put(child, parent);
            if (parentChildrenMap.get(parent) == null) {
                parentChildrenMap.put(parent, new LinkedList<Integer>());
            }
            parentChildrenMap.get(parent).add(child);
        }
    }

    /* Constructs a map containing Level-Nodes information */
    private void constructLevelNodeMap() {
        Queue<int[]> queue = new LinkedList();
        int[] root = {0,0};
        queue.add(root);
        treeNodes[0].degree = 0;

        while (!queue.isEmpty()) {
            int[] currentNode = queue.remove();
            int nodeValue = currentNode[0];
            int level = currentNode[1];
            //add node to levelNodeMap
            if (levelNodeMap.get(level) == null) {
                levelNodeMap.put(level, new LinkedList<Integer>());
            }
            levelNodeMap.get(level).add(nodeValue);
            treeNodes[nodeValue].degree = level;

            //get children of the node and put them into queue
            if (parentChildrenMap.get(nodeValue) != null) {
                LinkedList<Integer> children = parentChildrenMap.get(nodeValue);
                int nextlevel = level +1;
                height = nextlevel;
                for (Integer child : children) {
                    int[] nextNode = {child, nextlevel};
                    queue.add(nextNode);
                }
            }
        }
    }

    /* Prints out Child-Parent information */
    public void displayChildParent() {
        Iterator i = childParentMap.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry)i.next();
            System.out.println(pair.getKey() + "," +  pair.getValue());
        }
    }

    /* Prints out Parent-Children information */
    public void displayParentChildren() {
        displayMapIntToList(parentChildrenMap);
    }

    /* Prints out Level-Nodes information */
    public void displayLevelNodeMap() {
        displayMapIntToList(levelNodeMap);
    }

    /* Hel[per method for printing out complex map */
    private void displayMapIntToList(HashMap<Integer, LinkedList<Integer>> map) {
        Iterator i = map.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry)i.next();
            System.out.print("\n" + pair.getKey() + ",");
            for (Integer in : (LinkedList<Integer>)pair.getValue()) {
                System.out.print(" " + in);
            }
        }
        //  added a space to adjust the output format
        System.out.println("");
    }

    /* attaches random urls to nodes from a given file */
    public void addUrlToNodes(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (int i = 0; i < n; i ++) {
            treeNodes[i].setUrl(br.readLine());
            System.out.println(treeNodes[i].Url);
        }
        br.close();
    }

    /* Prints list of nodes of the given level */
    public ArrayList<Node> nodeListByLevel(int level) {
        ArrayList<Node> nodesList = new ArrayList();
        if (level>getHeight()) {
            System.out.println("No such level in the tree! " + level + " is greater than the height " + getHeight());
            return null;
        } else if (level < 0) {
            System.out.println("Level can not be negative!");
            return null;
        } else {
            System.out.print("Level " + level + " has nodes: ");
            LinkedList<Integer> ll = levelNodeMap.get(level);
            for (int i : ll) {
                System.out.print(i + " ");
                nodesList.add(treeNodes[i]);
            }
            System.out.println();
            return nodesList;
        }
    }

    /* Prints list of children of the given node
     * or states if the given node is a leaf */
    public ArrayList<Node> getChildren(int nodeNum) {
        ArrayList<Node> nodesList = new ArrayList();
        if (nodeNum >= tree.size() || nodeNum < 0) {
            System.out.println("There is no " + nodeNum + " node in the tree!");
            return null;
        } else if (!parentChildrenMap.containsKey(nodeNum)) {
            System.out.println(nodeNum + " is a leaf node!");
        } else {
            System.out.print("The children of " + nodeNum + " is: ");
            LinkedList<Integer> ll = parentChildrenMap.get(nodeNum);
            for (int i : ll) {
                System.out.print(i + " ");
                nodesList.add(treeNodes[i]);
            }
            System.out.println();
        }
        return nodesList;
    }

    /* Prints out a parent of the given node
     * or states if the given node is a root */
    public Node getParent(int nodeNum) {
        if (nodeNum >= tree.size() || nodeNum < 0) {
            System.out.println("There is no " + nodeNum + " node in the tree!");
            return null;
        } else if (nodeNum == 0) {
            System.out.println(nodeNum + " is the root!");
            return treeNodes[0];
        } else {
            System.out.print("The parent of " + nodeNum + " is: ");
            int ll = childParentMap.get(nodeNum);
            System.out.println(ll);
            return treeNodes[ll];
        }
    }

    /* returns the height of the tree */
    public int getHeight() {
        return height;
    }
}