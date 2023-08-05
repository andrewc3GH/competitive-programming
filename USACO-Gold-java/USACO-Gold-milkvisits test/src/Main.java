import java.io.*;
import java.util.*;

//look at queries once

class Main {  
  public static void main(String[] args) throws IOException {
    /*
    PrintWriter input = new PrintWriter(new BufferedWriter(new FileWriter(new File("milkvisits.in"))));
    int num = 1000;
    input.println(num + " " + 0);
    for (int i = 0; i < num; i++) {
        input.print(1 + " ");
    }
    input.println();
    for (int i = 0; i < num; i++) {
        input.println((i + 1) + " " + (i + 2));
    }
    input.close();
    */
    try {
        Scanner s = new Scanner(new File("milkvisits.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int[] cowArray = new int[N];
    for (int i = 0; i < N; i++) {
        cowArray[i] = s.nextInt();
    }
    
    Node.nodeLst = new Node[N];
    for (int i = 0; i < N; i++) {
        Node.nodeLst[i] = new Node(i, cowArray[i]);
    }
    for (int i = 0; i < N - 1; i++) {
        int a = s.nextInt() - 1;
        int b = s.nextInt() - 1;
        Node.nodeLst[a].addChildren(b);
        Node.nodeLst[b].addChildren(a);
    }

    try {
        Node.nodeLst[0].root(-1);
    } catch (Exception e) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("output.out"))));
        e.printStackTrace(pw);
    }

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
            Node.nodeLst[start - 1].queriesOnce.add(i);
            Node.nodeLst[end - 1].queriesOnce.add(i);
        } else {
            Node.nodeLst[start - 1].queriesTwice.add(i);
        }
        
    }

    //nodeLst[0].process();

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
        
    } catch (Exception e) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("milkvisits.out"))));
        e.printStackTrace(pw);
        pw.close();
    }
    
    
  }
}

class Node {
    public int index;
    public int type;
    public int parent;
    public HashSet<Integer> children;
    public HashSet<Integer> queriesOnce;
    public HashSet<Integer> queriesTwice;
    public static boolean[] queryAns;
    public static int[] queryMilk;
    public static Node[] nodeLst;
    
    public Node (int a, int b) {
        index = a;
        type = b;
        children = new HashSet<>();
        queriesOnce = new HashSet<Integer>();
        queriesTwice = new HashSet<Integer>();
    }
    
    public void addChildren(int a) {
        children.add(a);
    }    
        
    public void root(int p) throws Exception {
        System.out.println(p + " " + index);
        parent = p;
        // Remove parent from list of children
        // Call root(this) on children
        
        children.remove(p);
        for (int child : children) {
            try {
                nodeLst[child].root(index);
            } catch (Exception e) {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("output.out"))));
                e.printStackTrace(pw);
            }
           
            //System.out.println("root" + " " + child.index);
        }
    }

}