import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    //Scanner s = new Scanner(new File("farmupdates.in"));
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int Q = s.nextInt();

    int[] ans = new int[N];

    boolean[] active = new boolean[N];
    ArrayList<HashSet<Integer>> connections = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      ans[i] = Q;
      active[i] = true;
      connections.add(new HashSet<>());
    }
    int[][] roads = new int[Q][2];
    int roadNum = 0;
    for (int i = 0; i < Q; i++) { //loop through every query
      String letter = s.next();
      int num = 0;
      if (letter.equals("A")) {
        int a = s.nextInt() - 1;
        int b = s.nextInt() - 1;
        connections.get(a).add(b);
        connections.get(b).add(a);
        roads[roadNum] = new int[]{a, b};
        roadNum += 1;
      } if (letter.equals("D")) { //close down
        active[s.nextInt() - 1] = false;
      } if (letter.equals("R")) { //delete road
        int index = s.nextInt() - 1;
        connections.get(roads[index][0]).remove(roads[index][1]);
        connections.get(roads[index][1]).remove(roads[index][0]);
      }

      boolean[] visited = new boolean[N];
      ArrayList<Integer> queue = new ArrayList<>();
      int queueIndex = 0;
      
      for (int j = 0; j < N; j++) { //bfs
        if (ans[j] != Q || visited[j]) {

        } else {
          
          queue.add(j);
          boolean isActive = false;
          ArrayList<Integer> justSeen = new ArrayList<>();

          while (queueIndex < queue.size()) {
            int current = queue.get(queueIndex);
            queueIndex += 1;

            justSeen.add(current);
            if (active[current] == true) {
              isActive = true;
            }
            for (int neighbor : connections.get(current)) {
              if (visited[neighbor] == false) {
                queue.add(neighbor);
                visited[neighbor] = true;
              }
            }
            visited[current] = true;
          }
          if (isActive == false) {
            for (int a : justSeen) {
              ans[a] = Math.min(ans[a], i);
            }
          }
        }
      }
    }
    //System.out.println(Arrays.toString(ans));
    //UF unionFind = new UF(N);
    
    //PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("testsforhaybales.out"))));
    for (int i = 0; i < N; i++) {
      System.out.println(ans[i]);
    }
    //pw.close();
  }
}

class UF {
  int[] parents;  
  int[] activated;

  public UF(int N) {
      parents = new int[N];
      activated = new int[N];
      for (int i = 0; i < N; i++) {
          parents[i] = i;
          activated[i] = 1;
      }
  }

  public void merge(int a, int b) {
      //parents[getRoot(a)] = getRoot(b);
  }

  public int getRoot(int a) {
      if (parents[a] == a) {
          return a;
      } else {
          //parents[a] = getRoot(parents[a]);
          return getRoot(parents[a]);
      }
  }

  public boolean isConnected(int a, int b) {
      return (getRoot(a) == getRoot(b));
  }
}