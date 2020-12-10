import java.io.*;
import java.util.*;

class Main {  
  public static HashMap<Integer, ArrayList<Integer>> connectionDictionary;
  public static HashSet<Integer> visited;

/*
  public static class Node {
    public int number = 0;
    public int[] coor = new int[2];
    public HashSet<Node> connected = new HashSet<Node>();
    public Node(int number, int[] coor) {
      this.number = number;
      this.coor = coor;
    }
    public void addConection(Node number) {
      this.connected.add(number);
      number.connected.add(this);
    }
  }
*/

  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("fenceplan.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int[][] coorArray = new int[N][2];
    for (int i = 0; i < N; i++) {
      int[] tempArray = new int[2];
      tempArray[0] = s.nextInt();
      tempArray[1] = s.nextInt();
      coorArray[i] = tempArray;
    }
    int[][] connectionArray = new int[M][2];
    for (int i = 0; i < M; i++) {
      int[] temporary = new int[2];
      temporary[0] = s.nextInt();
      temporary[1] = s.nextInt();
      connectionArray[i] = temporary;
    }
    /*int[][] connectionArrayCopy = Arrays.copyOf(connectionArray, M);
    Arrays.sort(connectionArray, (int[] a, int[] b) -> {
      if (a[0] > b[0]){
        return 1;
      } else {
        return -1;
      }
    });
    Arrays.sort(connectionArrayCopy, (int[] a, int[] b) -> {
      if (a[1] > b[1]){
        return 1;
      } else {
        return -1;
      }
    });*/
    
    /*
    Node[] nodeArray = new Node[N];
    for (int i = 0; i < N; i++) {
      Node n = new Node(i + 1, coorArray[i]);
      nodeArray[i] = n;
    }
    */

    connectionDictionary = new HashMap<>();
    for (int j = 0; j < N; j++) {
      connectionDictionary.put(j + 1, new ArrayList<Integer>());
    }
    for (int k = 0; k < M; k++) {
      int[] currentConnection = connectionArray[k];
      ArrayList<Integer> add1 = connectionDictionary.get(currentConnection[0]);
      add1.add(currentConnection[1]);
      connectionDictionary.put(currentConnection[0], add1);
      ArrayList<Integer> add2 = connectionDictionary.get(currentConnection[1]);
      add2.add(currentConnection[0]);
      connectionDictionary.put(currentConnection[1], add2);
    }

    ArrayList<HashSet<Integer>> groupList = new ArrayList<>();
    visited = new HashSet<>();
    for (int i = 0; i < N; i++) {
      groupList.add(linkConnections(i + 1, new HashSet<Integer>()));
    }

    System.out.println(groupList);

    int minPerimeter = 1000000000;
    for (HashSet<Integer> array : groupList) {
      if (array.size() > 0) {
        int maxLeft = 0;
        int maxRight = 0;
        int maxUp = 0;
        int maxDown = 0;
        for (int i : array) {
          maxLeft = coorArray[i - 1][0];
          maxRight = coorArray[i - 1][0];
          maxUp = coorArray[i - 1][1];
          maxDown = coorArray[i - 1][1];
          break;
        }
        for (int i : array) {
          if (coorArray[i - 1][0] < maxLeft) {
            maxLeft = coorArray[i - 1][0];
          } if (coorArray[i - 1][0] > maxRight) {
            maxRight = coorArray[i - 1][0];
          } if (coorArray[i - 1][1] < maxDown) {
            maxDown = coorArray[i - 1][1];
          } if (coorArray[i - 1][1] > maxUp) {
            maxUp = coorArray[i - 1][1];
          }
        }
        int perimeter = 2*(maxRight - maxLeft) + 2*(maxUp - maxDown);
        if (minPerimeter > perimeter) {
          minPerimeter = perimeter;
        }
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
    pw.println(minPerimeter);
    pw.close();
  }

  public static HashSet linkConnections(int number, HashSet<Integer> set) {
    for (int i : connectionDictionary.get(number)) {
      if (!set.contains(i) && !visited.contains(i)) {
        set.add(i);
        visited.add(i);
        linkConnections(i, set);
      }
    }
    return set;
  }
  //maybe create recursive function to find complete connections and store in arraylist of hashsets/arraylists
}