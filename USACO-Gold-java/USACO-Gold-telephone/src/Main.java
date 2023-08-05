import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    //Scanner s = new Scanner(new File("telephone.in"));
    int N = s.nextInt(); //num cows
    int K = s.nextInt(); //num breeds
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt() - 1;
    }
    s.nextLine();
    int[][] adjMatrix = new int[K][K];
    for (int i = 0; i < K; i++) {
      String line = s.nextLine();
      for (int j = 0; j < K; j++) {
        adjMatrix[i][j] = Integer.parseInt(line.substring(j, j + 1));
      }
    }

    //breed that sent message, index, distance travelled
    ArrayDeque<int[]> queue = new ArrayDeque<>();
    queue.addFirst(new int[]{array[0], 0, 0});

    //location, breed
    boolean[][] visited = new boolean[N][K];
    visited[0][array[0]] = true;
    
    int ans = 0;
    while (queue.size() > 0) {
      int[] a = queue.pollFirst(); 
      int breed = a[0]; //that sent message
      int index = a[1];
      int distance = a[2];

      if (index == N - 1 && breed == array[index] && adjMatrix[breed][array[index]] == 0) {

      } else {
        visited[index][breed] = true;

        //compute stuff here
        if (!visited[index][array[index]] && adjMatrix[breed][array[index]] == 1) {
          if (index == N - 1) {
            ans = distance;
            break;
          }
          queue.addFirst(new int[]{array[index], index, distance});
          visited[index][array[index]] = true;
        }

        if (index + 1 < N && !visited[index + 1][breed]) { //go to the right
          queue.addFirst(new int[]{breed, index + 1, distance + 1});
          //visited[index + 1][breed] = true;
        }
        if (index - 1 > -1 && !visited[index - 1][breed]) { //go to the left
          queue.addLast(new int[]{breed, index - 1, distance + 1});
          //visited[index - 1][breed] = true;
        }
      }
    }
    if (ans == 0) {
      System.out.println(-1);
    }
    System.out.println(ans);
  }
}

//bfs with N by K array