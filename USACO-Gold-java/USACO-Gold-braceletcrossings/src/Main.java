import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("braceletcrossings.in"));
    int T = s.nextInt(); //num cases
    
    for (int i = 0; i < T; i++) {
        int N = s.nextInt(); //num bracelets
        int M = s.nextInt(); //num lines
        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
        for (int j = 0; j < M; j++) {
            lines.add(new ArrayList<>());
            int lineLen = s.nextInt();
            lines.get(j).add(0);
            for (int k = 0; k < lineLen; k++) {
                lines.get(j).add(s.nextInt());
            }
            lines.get(j).add(0);
        }
        System.out.println(i + ": " + lines);
        
        //make over arching node -> nodeLst[0]

        Node[] nodeLst = new Node[N + 1];
        for (int j = 0; j < N + 1; j++) {
            nodeLst[j] = new Node(j);
        }
        //check if wrong parent, check if out of order

        for (int j = 0; j < M; j++) { //loop through every line
            ArrayList<Integer> line = lines.get(j);
            ArrayList<Integer> seen = new ArrayList<>();
            seen.add(0);
            for (int k = 1; k < line.size() + 1; k++) { //loop through line
                //nodeLst[line.get(k)].checkParent();
            }
        }

    }
    
    }
}

/*
class Node {
    public int color;
    public Node parent;
    public ArrayList<Node> children;
    public boolean hasParent;
    public boolean nullParent;
    
    public Node (int a) {
        color = a;
        children = new ArrayList<>();
    }

    public void setParent (Node a) {
        parent = a;
        if (a == null) {
            nullParent = true;
        }
        hasParent = true;
    }

    public void addChild (Node a) {
        children.add(a);
    }

    public boolean checkParent() {
        if (hasParent) {
            
        }
        return true;
    }

}
*/