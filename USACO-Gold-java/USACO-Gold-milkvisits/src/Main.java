import java.io.*;
import java.util.*;

//for each node, find and store all of its ancestors
//when comparing two nodes, find their lowest common ancestor and compute the two distances
//manually compute the distances -> use prefix sums or smth idk

//group lowestcommonancestor and findans functions
//use smth similar to prefix sums to solve

//try storing prefix sum values only in leaf nodes

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("milkvisits.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int[] cowArray = new int[N];
    for (int i = 0; i < N; i++) {
        cowArray[i] = s.nextInt();
    }
    
    Node[] nodeLst = new Node[N];
    for (int i = 0; i < N; i++) {
        nodeLst[i] = new Node(i, cowArray[i]);
    }
    for (int i = 0; i < N - 1; i++) {
        int a = s.nextInt() - 1;
        int b = s.nextInt() - 1;
        nodeLst[a].addChildren(nodeLst[b]);
        nodeLst[b].addChildren(nodeLst[a]);
    }

    //nodeLst[0].root(null, 0);

    for (int i = 0; i < N; i++) {
        //Node a = nodeLst[i];
        //a.findAncestors();
        //System.out.println(a.ancestors);
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("milkvisits.out"))));
    for (int i = 0; i < M; i++) {
       int a = s.nextInt() - 1;
       int b = s.nextInt() - 1;
       int type = s.nextInt();
       //Node lowestAncestor = nodeLst[a].findLowestAncestor(nodeLst[b]);
       
       /*
       if (nodeLst[a].containsType(lowestAncestor, type) || nodeLst[b].containsType(lowestAncestor, type)) {
        pw.print(1);
       } else {
         pw.print(0);
       }
       */
      /*
      if (nodeLst[a].containsColor(nodeLst[b], type)) {
        pw.print(1);
       } else {
         pw.print(0);
       }
       */
       //System.out.println("next");
       
    }
    //pw.print("10110");
    
 
    pw.println();
    
    pw.close();
  }
}

/*

class Node {
    public int index;
    public int type;
    public Node parent;
    public HashSet<Node> children;
    //public ArrayList<Node> ancestors;
    public int height; //from top (root is 0);
    
    public Node (int a, int b) {
        index = a;
        type = b;
        children = new HashSet<>();
        //ancestors = new ArrayList<>();
    }
    
    public void setParent(Node a) {
        parent = a;
    }
    
    public void addChildren(Node a) {
        children.add(a);
    }    
        
    public void root(Node p, int depth) {
        parent = p;
        // Remove parent from list of children
        // Call root(this) on children
        children.remove(p);
        for (Node child : children) {
            child.root(this, depth + 1);
        }
        height = depth;
    } 
    
    public void findAncestors() {
        ancestors.add(this);
        for (Node child : children) {
            child.ancestors = new ArrayList<>(ancestors);
            //child.ancestors.add(this);
            child.findAncestors();
        }
    }
    
    public Node findLowestAncestor(Node other) {
        Node last = null;
        for (int i = 0; i < Math.min(ancestors.size(), other.ancestors.size()); i++) {
            if (ancestors.get(i).index != other.ancestors.get(i).index) {
                return last;
            }
            last = ancestors.get(i);
        }
        
        return last;
    }
    
    public boolean containsType(Node ancestor, int newType) {
        //System.out.println(ancestor);
        //System.out.println(ancestors);

        for (int i = ancestors.size() - 1; i > 0; i--) {
            if (ancestors.get(i).type == newType) {
                return true;
            }
            if (ancestors.get(i).index == ancestor.index) {
                break;
            }
        }
        return false;
    }
    
    public boolean containsColor(Node other, int newType) {
        Node a = this;
        Node b = other;
        while (a.height != b.height) {
            if (a.type == newType || b.type == newType) {
                return true;
            }
            if (a.height > b.height) {
                a = a.parent;
            } else {
                b = b.parent;
            }
        }
        if (a.index == b.index) {
            return a.type == newType;
        }
        while (true) {
            if (a.type == newType || b.type == newType) {
                return true;
            }
            a = a.parent;
            b = b.parent;
            if (a == null) {
                break;
            } else if (a.index == b.index) {
                return a.type == newType;
                
            }
        }
        //System.out.println(a.type + " " + b.type + " " + newType);
        
        return false;
    }
}

*/