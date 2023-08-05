import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {

    //prims but have an array of the current minimum distances to each node
    //loop n times: find lowest distance that is unvisited, going to that node and finding all its neighbors, update the array
    //note, find maximum values instead of minimum values

    Scanner s = new Scanner(new File("superbull.in"));
    int N = s.nextInt();

    int[] array = new int[N];
    
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    int[][] products = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        products[i][j] = array[i] ^ array[j];
      }
    }

    int[] maxArray = new int[N];
    maxArray[0] = 0;
    for (int i = 1; i < N; i++) {
      maxArray[i] = -1;
    }
    
    HashSet<Integer> visited = new HashSet<>();

    long sum = 0;

    for (int i = 0; i < N; i++) {
      int currentIndex = -1;
      int currentDistance = -1;
      for (int j = 0; j < N; j++) {
        if (currentDistance < maxArray[j] && !visited.contains(j)) {
          currentIndex = j;
          currentDistance = maxArray[j];
        }
      }
      visited.add(currentIndex);
      sum += currentDistance;
      for (int k = 0; k < N; k++) {
        if (k != currentIndex) {
          maxArray[k] = Math.max(maxArray[k], products[currentIndex][k]);
        }
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
    pw.println(sum);
    pw.close();
    
  }
}