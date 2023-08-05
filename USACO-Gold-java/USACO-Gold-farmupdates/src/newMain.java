import java.io.*;
import java.util.*;

class newMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("farmupdates.in"));
    //Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int Q = s.nextInt();

    int[] ans = new int[N];

    boolean[] active = new boolean[N];
    ArrayList<HashSet<Integer>> connections = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      ans[i] = Q;
      connections.add(new HashSet<>());
      active[i] = true;
    }
    
    HashSet<Integer> roadsActive = new HashSet<>();

    String[] letters = new String[Q];
    int[][] nums = new int[Q][2];
    int[][] roads = new int[Q][2];
    int roadNum = 0;
    for (int i = 0; i < Q; i++) {
      letters[Q - i - 1] = s.next();
      if (letters[Q - i - 1].equals("A")) {
        nums[Q - i - 1][0] = s.nextInt() - 1;
        nums[Q - i - 1][1] = s.nextInt() - 1;
        roads[roadNum][0] = nums[Q - i - 1][0];
        roads[roadNum][1] = nums[Q - i - 1][1];
        roadsActive.add(roadNum);
        roadNum += 1;

      } else {
        nums[Q - i - 1][0] = s.nextInt() - 1;
        if (letters[Q - i - 1].equals("D")) {
          active[nums[Q - i - 1][0]] = false;
        } else {
          roadsActive.remove(nums[Q - i - 1][0]);
          
        }
      }
    }
    for (int a : roadsActive) {
      connections.get(roads[a][0]).add(roads[a][1]);
      connections.get(roads[a][1]).add(roads[a][0]);
    }

    boolean[] relevant = new boolean[N];
    //bfs to initialize all relevant farms (at the end)
    for (int i = 0; i < N; i++) {
      if (active[i]) {
        //relevant[i] = true;
        DFS(i, connections, relevant, ans, Q);
        //System.out.println(i + " start");
      }
      
    }

    for (int i = 0; i < Q; i++) {
      if (letters[i].equals("R")) {
        int index = nums[i][0];
        connections.get(roads[index][0]).add(roads[index][1]);
        connections.get(roads[index][1]).add(roads[index][0]);
        if (relevant[roads[index][0]] == false && relevant[roads[index][1]] == false) {
          //dont do anything
        } else if (relevant[roads[index][0]] == true && relevant[roads[index][1]] == true) {
          //dont do anything
        } else {
          DFS(roads[index][0], connections, relevant, ans, Q - i - 1);
          DFS(roads[index][1], connections, relevant, ans, Q - i - 1);
          //System.out.println(roads[index][0] + " " + roads[index][1] + "R");
        }
      } else if (letters[i].equals("D")) {
        DFS(nums[i][0], connections, relevant, ans, Q - i - 1);
        //System.out.println(nums[i][0] + " " + i);
      }
    }
    for (int i = 0; i < N; i++) {
      System.out.println(ans[i]);
    }
    //System.out.println(Arrays.toString(ans));
  }

  public static void DFS(int index, ArrayList<HashSet<Integer>> connections, boolean[] relevant, int[] ans, int b) {
    if (relevant[index]) {
      return; //do nothing if current index is already relevant
    } else {
      relevant[index] = true;
      ans[index] = b;
      //System.out.println(index + " " + connections);
      for (int a : connections.get(index)) {
        if (!relevant[a]) {
          DFS(a, connections, relevant, ans, b);
        }
      }
    }
  }
}    