import java.io.*;
import java.util.*;

/*

make node class instead

*/
/*
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
}*/

class newMain {  
  public static Node[] nodeLst;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("barnpainting.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    ArrayList[] connections = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      connections[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < N - 1; i++) {
      int one = s.nextInt();
      int two = s.nextInt();
      connections[one - 1].add(two - 1);
      connections[two - 1].add(one - 1);
    }

    Node[] nodeLst = new Node[N];
    for (int i = 0; i < N; i++) {
      nodeLst[i] = new Node(i);
    }

    ArrayList<Integer> queue = new ArrayList<>();
    int queueIndex = 0;
    queue.add(0);

    boolean[] visited = new boolean[N];
    visited[0] = true;

    while (queueIndex < queue.size()) {
      int currentNode = queue.get(queueIndex);
      queueIndex += 1;

      ArrayList<Integer> neighbors = connections[currentNode];
      for (int i = 0; i < neighbors.size(); i++) {
        int neighbor = neighbors.get(i);
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          nodeLst[currentNode].addChild(nodeLst[neighbor]);
          queue.add(neighbor);
        }
      }
    }

    //print nodes
    /*
    for (int i = 0; i < N; i++) {
      String a = (i + 1) + " ";
      for (int j = 0; j < nodeLst[i].children.size(); j++) {
        a += (nodeLst[i].children.get(j).index + 1) + " ";
      }
      System.out.println(a);
    }*/

    for (int i = 0; i < K; i++) {
      int index = s.nextInt();
      int val = s.nextInt();
      nodeLst[index - 1].array = new long[]{0, 0, 0};
      nodeLst[index - 1].array[val - 1] = 1;
    }

    goBack(nodeLst[0]);
    int answer = (int) (nodeLst[0].array[0] + nodeLst[0].array[1] + nodeLst[0].array[2]) % 1000000007;

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("barnpainting.out"))));
    pw.println(answer);
    pw.close();
    
  }

  public static void goBack(Node currentNode) {
    for (int i = 0; i < currentNode.children.size(); i++) {
      Node child = currentNode.children.get(i);
      goBack(child);
      currentNode.array[0] = (currentNode.array[0] * (child.array[1] + child.array[2])) % (long)1000000007;
      currentNode.array[1] = (currentNode.array[1] * (child.array[0] + child.array[2])) % (long)1000000007;
      currentNode.array[2] = (currentNode.array[2] * (child.array[0] + child.array[1])) % (long)1000000007;
    }
  }
  /*
  public static void goBack(int nodeIndex) {
    //int[] a = {available[nodeIndex][0], available[nodeIndex][1], available[nodeIndex][2]};
    for (int i = 0; i < children[nodeIndex].size(); i++) {
      int b = (int)children[nodeIndex].get(i);
      goBack((int)children[nodeIndex].get(i));
      available[nodeIndex][0] = (available[nodeIndex][0] * (available[b][1] + available[b][2])) % 1000000007;
      available[nodeIndex][1] = (available[nodeIndex][1] * (available[b][0] + available[b][2])) % 1000000007;
      available[nodeIndex][2] = (available[nodeIndex][2] * (available[b][0] + available[b][1])) % 1000000007;
    }
  }
  */
}
