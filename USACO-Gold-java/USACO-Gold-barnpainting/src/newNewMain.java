import java.io.*;
import java.util.*;

class Node {
  public int index; 
  public ArrayList<Node> children;
  public long[] array;

  public Node(int num) {
    index = num;
    children = new ArrayList<Node>();
    array = new long[]{1, 1, 1};
  }
  public void addChild(Node c) {
    children.add(c);
  }
  public void root(Node parent) {
    if (children.contains(parent)) {
        children.remove(parent);
    }
    for (Node child : children) {
        child.root(this);
    }
  }
  public void goBack() {
    for (Node child : children) {
      child.goBack();
      array[0] = (array[0] * (child.array[1] + child.array[2])) % (long)1000000007;
      array[1] = (array[1] * (child.array[0] + child.array[2])) % (long)1000000007;
      array[2] = (array[2] * (child.array[0] + child.array[1])) % (long)1000000007;
    }
  }
}

class newNewMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("barnpainting.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    
    Node[] nodeLst = new Node[N];
    for (int i = 0; i < N; i++) {
      nodeLst[i] = new Node(i);
    }
    
    for (int i = 0; i < N - 1; i++) {
      int one = s.nextInt() - 1;
      int two = s.nextInt() - 1;
      nodeLst[one].children.add(nodeLst[two]);
      nodeLst[two].children.add(nodeLst[one]);
    }

    for (int i = 0; i < K; i++) {
      int index = s.nextInt() - 1;
      int val = s.nextInt() - 1;
      nodeLst[index].array = new long[]{0, 0, 0};
      nodeLst[index].array[val] = 1;
    }
    
    nodeLst[0].root(null);
    nodeLst[0].goBack();
    int answer = (int) (nodeLst[0].array[0] + nodeLst[0].array[1] + nodeLst[0].array[2]) % 1000000007;

    PrintStream pw = new PrintStream("barnpainting.out");
    pw.println(answer);
    pw.close();
    
  }
}