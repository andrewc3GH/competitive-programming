import java.io.*;
import java.util.*;

class Main {  

  /*
  graph, bidirectional, weighted
  adj matrix for edges, int[][]
  initial shortest path between house and barn, keep track of what nodes/edges are passed through
  go through passed through edges, double the length of one of them, run shortest path, compare to initial shortest path
  after passing through each edge, reset the length
  */
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("rblock.in"));
    int N = s.nextInt(); //numbarns
    int M = s.nextInt(); //numroads

    int[][] adjMatrix = new int[N + 1][N + 1];
    for (int i = 0; i < N + 1; i++) {
      for (int j = 0; j < N + 1; j++) {
        if (i != j) {
          adjMatrix[i][j] = 999999999;
        }
      }
    }
    for (int i = 0; i < M; i++) {
      int one = s.nextInt();
      int two = s.nextInt();
      int three = s.nextInt();
      adjMatrix[one][two] = three;
      adjMatrix[two][one] = three;
    }

    int[] closest = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      closest[i] = 99999999;
    }
    closest[1] = 0;

    ArrayList[] paths = new ArrayList[N + 1];
    for (int i = 0; i < N + 1; i++) {
      paths[i] = new ArrayList<Integer>();
    }

    boolean[] visited = new boolean[N + 1];

    int[] lastLocationArr = new int[N + 1];

    while (true) {
      int newLocation = 0;
      int minDist = 99999998;
      for (int i = 1; i < N + 1; i++) {
        if (!visited[i] && closest[i] < minDist) {
          minDist = closest[i];
          newLocation = i;
        }
      }

      paths[newLocation] = new ArrayList<>(paths[lastLocationArr[newLocation]]);
      paths[newLocation].add(newLocation);
      visited[newLocation] = true;

      if (newLocation == N) {
        break;
      }
      
      for (int i = 1; i < N + 1; i++) {
        closest[i] = Math.min(closest[i], adjMatrix[newLocation][i] + closest[newLocation]);
        if (closest[i] == adjMatrix[newLocation][i] + closest[newLocation] && i != newLocation) {
          lastLocationArr[i] = newLocation;
        }
      }
      System.out.println(Arrays.toString(lastLocationArr));
    }

    //find path algorithm (not used)
    int index = N;
    ArrayList<Integer> newPath = new ArrayList<>();
    newPath.add(index);
    while (true) {
      index = lastLocationArr[index];
      newPath.add(index);
      
      if (index == 1) {
        break;
      }
    }
    System.out.println(newPath);

    //System.out.println(paths[N]);

    //paths[N] <- find initial len
    int initialLen = 0;
    /*
    for (int i = 1; i < paths[N].size(); i++) {
      initialLen += adjMatrix[(int)paths[N].get(i)][(int)paths[N].get(i - 1)];
    }
    System.out.println(initialLen);
    */
    initialLen = closest[N];

    int ans = 0;
    
    for (int j = 1; j < paths[N].size(); j++) {
      adjMatrix[(int)paths[N].get(j)][(int)paths[N].get(j - 1)] *= 2;
      adjMatrix[(int)paths[N].get(j - 1)][(int)paths[N].get(j)] *= 2;

      closest = new int[N + 1];
      for (int i = 1; i < N + 1; i++) {
        closest[i] = 99999999;
      }
      closest[1] = 0;

      visited = new boolean[N + 1];

      int[] sums = new int[N + 1];
      while (true) {
        int newLocation = 0;
        int minDist = 99999998;
        for (int i = 1; i < N + 1; i++) {
          if (!visited[i] && closest[i] < minDist) {
            minDist = closest[i];
            newLocation = i;
          }
        }
        
        visited[newLocation] = true;
        if (newLocation == N) {
          break;
        }
        for (int i = 1; i < N + 1; i++) {
          closest[i] = Math.min(closest[i], adjMatrix[newLocation][i] + closest[newLocation]);
        }
      }
      ans = Math.max(ans, closest[N]);


      adjMatrix[(int)paths[N].get(j)][(int)paths[N].get(j - 1)] /= 2;
      adjMatrix[(int)paths[N].get(j - 1)][(int)paths[N].get(j)] /= 2;
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("rblock.out"))));
    pw.println(ans - initialLen);
    pw.close();
  }
}