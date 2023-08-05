import java.io.*;
import java.util.*;

public class Main {  

  /*
  hashmap -> key is x or y coor, value is list of every node that has this x or y coor
  make a hashmap for both x and y coors
  run bfs algo from the starting node
  answer is the number of bfs iterations until reaching the end node
  */
  public static void main(String[] args) throws Exception {
    Scanner s = new Scanner(new File("lasers.in"));
    int N = s.nextInt();
    int[] startCoor = new int[]{s.nextInt(), s.nextInt()};
    int[] endCoor = new int[]{s.nextInt(), s.nextInt()};
    
    int[][] coors = new int[N][2];
    for (int i = 0; i < N; i++) {
      coors[i] = new int[] {s.nextInt(), s.nextInt()};
    }
    HashMap<String, String> capitalCities = new HashMap<String, String>();
    HashMap<String, Integer> people = new HashMap<String, Integer>();
    System.out.println("adf");
    /*HashMap<Integer, ArrayList<Integer>> vals = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> xCoors = new HashMap<Integer, ArrayList<Integer>>();
    for (int i = 0; i < N; i++) {
      xCoors.put(coors[i][0], new ArrayList<Integer>());
    }*/

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("lasers.out"))));
    pw.println();
    pw.close();
  }
}