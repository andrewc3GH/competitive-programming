import java.io.*;
import java.util.*;

class Main {  
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
    int[][] available = new int[N][3];
    for (int i = 0; i < N; i++) {
      available[i][0] = 0;
      available[i][1] = 0;
      available[i][2] = 0;
    }
    int first = 0;
    boolean[] given = new boolean[N];
    for (int i = 0; i < K; i++) {
      int one = s.nextInt() - 1;
      available[one][0] = 0;
      available[one][1] = 0;
      available[one][2] = 0;
      available[one][s.nextInt() - 1] = 1;
      given[one] = true;
      first = one;
    }

    for (int i = 0; i < N; i++) {
      if (connections[i].size() == 1) {
        first = i;
      }
    }

    ArrayList<Integer> queue = new ArrayList<>();
    int queueIndex = 0;
    queue.add(first);

    boolean[] visited = new boolean[N];
    visited[first] = true;

    while (queueIndex < queue.size()) {
      int currentNum = queue.get(queueIndex);
      queueIndex += 1;
      
      /*
      int[] newAvailability = new int[3];
      newAvailability[0] = true;
      newAvailability[1] = true;
      newAvailability[2] = true;
      if (available[currentNum][0]) {
        newAvailability[0] = false;
      } if (available[currentNum][1]) {
        newAvailability[1] = false;
      } if (available[currentNum][2]) {
        newAvailability[2] = false;
      }
      */

      ArrayList<Integer> neighbors = connections[currentNum];
      for (int i = 0; i < neighbors.size(); i++) {
        int neighbor = neighbors.get(i);
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          if (given[neighbor]) {
            if (available[neighbor][0] == 1) {
              available[neighbor][0] = available[currentNum][1] + available[currentNum][2];
            } else if (available[neighbor][1] == 1) {
              available[neighbor][1] = available[currentNum][0] + available[currentNum][2];
            } else {
              available[neighbor][2] = available[currentNum][0] + available[currentNum][1];
            }
          } else {
            available[neighbor][0] = available[currentNum][1] + available[currentNum][2];
            available[neighbor][1] = available[currentNum][0] + available[currentNum][2];
            available[neighbor][2] = available[currentNum][0] + available[currentNum][1];
            //System.out.println(Arrays.toString(available[neighbor]) + " " + currentNum);
          }
          queue.add(neighbor);
        }
      }
    }
    
    int ans = 0;
    //System.out.println(Arrays.toString(connections));
    for (int i = 0; i < N; i++) {
      //System.out.println(i + " " + Arrays.toString(available[i]));
      if (connections[i].size() == 1 && i != first) {
        
        for (int j = 0; j < 3; j++) {
          ans += available[i][j];
        }
      }
    }
    

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("barnpainting.out"))));
    pw.println(ans);
    pw.close();
    
  }
}