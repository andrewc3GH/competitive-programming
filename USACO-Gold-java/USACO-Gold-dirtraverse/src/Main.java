import java.io.*;
import java.util.*;

class Node {
  String name;
  ArrayList<Node> children;
  Node parent;
  int numChildFiles;
  int childFilesLen;
  int topDist;
  int numFiles;
  public Node(String a) {
    children = new ArrayList<Node>();
    numChildFiles = 0;
    childFilesLen = 0;
    topDist = 0;
    name = a;
  }

  public void numFiles(int a) {
    numFiles = a;
  }

  public void setName(String a) {
    name = a;
  }
  public void setParent(Node a) {
    parent = a;
  }
  public void addChild(Node a) {
    children.add(a);
    a.setParent(this);
  }

  //to find the number of child files in this directory
  public int calcChildFiles() {
    if (children.size() == 0) {
      return 1;
    }
    for (int i = 0; i < children.size(); i++) {
      numChildFiles += children.get(i).calcChildFiles();
    }
    return numChildFiles;
  }

  //to find the number of characters of child files in this directory
  public int calcChildFileLen() {
    if (children.size() == 0) {
      childFilesLen = name.length();
      return name.length();
    }
    for (int i = 0; i < children.size(); i++) {
      Node child = children.get(i);
      if (child.children.size() > 0) {
        childFilesLen += (child.name.length() + 1) * child.numChildFiles;
      }
      childFilesLen += child.calcChildFileLen();
    }
    return childFilesLen;
  }

  //call on root
  public int calcTopDist() {
    if (parent == null) {
      topDist = 0;
    } else {
      topDist += parent.topDist - numChildFiles*(name.length() + 1) + parent.childFilesLen - childFilesLen + 3 * (numFiles - numChildFiles);
      if (children.size() == 0) {
        topDist -= 3;
      }
    }
    for (int i = 0; i < children.size(); i++) {
      Node child = children.get(i);
      child.calcTopDist();
    }
    return topDist;
  }

  public int calcAns() {
    return topDist + childFilesLen;
  }

}

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("dirtraverse.in"));
    int N = s.nextInt();
    String[] nameArray = new String[N];
    int numFiles = 0;
    //index in nameArray, list of neighbors
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    for (int i = 0; i < N; i++) {
      String name = s.next();
      nameArray[i] = name;
      int one = s.nextInt();
      ArrayList<Integer> lst = new ArrayList<>();
      //file
      if (one == 0) {
        map.put(i, lst);
        numFiles += 1;
      } else {
        for (int j = 0; j < one; j++) {
          int a = s.nextInt() - 1;
          lst.add(a);
        }
        map.put(i, lst);
      }
    }
    //System.out.println(map);

    Node[] nodeLst = new Node[N];
    for (int i = 0; i < N; i++) {
      nodeLst[i] = new Node(nameArray[i]);
      nodeLst[i].numFiles(numFiles);
    }

    for (int i = 0; i < N; i++) {
      Node a = nodeLst[i];
      //a.setName(nameArray[i]);
      //System.out.println(nodeLst[i].name);
      for (int j = 0; j < map.get(i).size(); j++) {
        a.addChild(nodeLst[map.get(i).get(j)]);
      }
    }
    //System.out.println("here");
    Node top = nodeLst[0];
    top.calcChildFiles();
    top.calcChildFileLen();
    top.calcTopDist();
    int minVal = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      minVal = Math.min(nodeLst[i].calcAns(), minVal);
      //System.out.println(nameArray[i] + " " + nodeLst[i].numChildFiles + " " + nodeLst[i].childFilesLen + " " + nodeLst[i].topDist);
    }

    /*
    boolean[] visited = new boolean[N];
    //start at Bessie
    //index in nameArray, num passed through
    ArrayList<int[]> queue = new ArrayList<>();
    queue.add(new int[]{0, 0});
    int queueIndex = 0;
    while (queueIndex < queue.size()) {
      int current = queue.get(queueIndex)[0];
      int num = queue.get(queueIndex)[1];
      queueIndex += 1;
      //check if file
      if (map.get(current).size() == 0) {
        System.out.println(nameArray[current] + " " + num);
      } else {
        for (int i = 0; i < map.get(current).size(); i++) {
          int neighbor = map.get(current).get(i);
          if (visited[neighbor] == false) {
            queue.add(new int[]{neighbor, num + 1});
            visited[neighbor] = true;
          }
          
        }
      }
    }*/

    

    //calculate "../" on each path of tree
    //calculate characters on each path of tree


   
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("dirtraverse.out"))));
    pw.println(minVal);
    pw.close();
  }
}