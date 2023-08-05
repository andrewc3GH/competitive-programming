import java.io.*;
import java.util.*;

//look at queries once

class newMain {  
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

    nodeLst[0].root(null);

    int[][] allQ = new int[M][3];
    Node.queryMilk = new int[M];
    Node.queryAns = new boolean[M];

    
    for (int i = 0; i < M; i++) {
        int start = s.nextInt();
        int end = s.nextInt();
        int milk = s.nextInt();
        allQ[i] = new int[]{start, end, milk};
        Node.queryMilk[i] = milk;

        if (start != end) {
            nodeLst[start - 1].queriesOnce.add(i);
            nodeLst[end - 1].queriesOnce.add(i);
        } else {
            nodeLst[start - 1].queriesTwice.add(i);
        }
        
    }

    nodeLst[0].process();

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("milkvisits.out"))));
    for (boolean a : Node.queryAns) {
        if (a == true) {
            pw.print(1);
        } else {
            pw.print(0);
        }
    }
    System.out.println("done;a");
    //pw.print(10110);
    
 
    pw.println();
    
    pw.close();
  }
}

class Node {
    public int index;
    public int type;
    public Node parent;
    public HashSet<Node> children;
    public HashSet<Integer> queriesOnce;
    public HashSet<Integer> queriesTwice;
    public static boolean[] queryAns;
    public static int[] queryMilk;
    
    public Node (int a, int b) {
        index = a;
        type = b;
        children = new HashSet<>();
        queriesOnce = new HashSet<Integer>();
        queriesTwice = new HashSet<Integer>();
    }
    
    public void setParent(Node a) {
        parent = a;
    }
    
    public void addChildren(Node a) {
        children.add(a);
    }    
        
    public void root(Node p) {
        parent = p;
        // Remove parent from list of children
        // Call root(this) on children
        
        children.remove(p);
        for (Node child : children) {
            child.root(this);
        }
    }

    public void process() {
        for (Node child : children) {
            child.process();
        }
        /*
        if (parent != null) {
            HashSet<Integer> intersection = new HashSet<>(queriesOnce);
            intersection.retainAll(parent.queriesOnce); //intersection between this.queriesOnce and parent.queriesOnce
            parent.queriesTwice.addAll(intersection);
            parent.queriesOnce.addAll(queriesOnce);
            for (int query : intersection) {
                parent.queriesOnce.remove(query);
            }
        }*/
        
        for (int query : queriesOnce) {
            /*
            if (this.type == queryMilk[query] || queryAns[query] == true) { //if too slow, fix this second part
                queryAns[query] = true;
            }
            */
            /*
            if (this.type == queryMilk[query] || queryAns[query] == true) { //if too slow, fix this second part
                queryAns[query] = true;
            } else {
                if (parent.queriesOnce.contains(query)) { //might be too slow
                    parent.queriesOnce.remove(query);
                    parent.queriesTwice.add(query);
                } else {
                    parent.queriesOnce.add(query);
                }
            }
            */
        }
        for (int query : queriesTwice) {
            /*
            if (this.type == queryMilk[query]) {
                queryAns[query] = true;
            }
            */
        }
        
    }
}