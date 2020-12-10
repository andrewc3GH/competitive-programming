import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("lifeguards.in"));
    int N = s.nextInt();
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i][0] = s.nextInt();
      array[i][1] = s.nextInt();
    }
    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });
    boolean noWorkNeeded = false;
    
    HashSet<Integer> lookAt = new HashSet<>();

    HashSet<Integer> removeIndexAll = new HashSet<>();
    for (int i = 0; i < N; i++) {
      ArrayList<Integer> removeIndex = new ArrayList<>();
      for (int index : lookAt) {
        if (array[i][0] >= array[index][0] && array[i][1] <= array[index][1]) {
          removeIndexAll.add(i);
          noWorkNeeded = true;
          break;
        } else {
          removeIndex.add(index);
        }
      }
      for (int j = 0; j < removeIndex.size(); j++) {
        lookAt.remove(removeIndex.get(j));
      }
      lookAt.add(i);
    }

    int totalSum = 0;
    for (int i = 0; i < N; i++) {
      if (!removeIndexAll.contains(i)) {
        totalSum += array[i][1] - array[i][0];
        for (int j = i + 1; j < N; j++) {
          if (!removeIndexAll.contains(j)) {
            if (array[i][1] >= array[j][0]) {
              totalSum -= array[i][1] - array[j][0];
            }
            break;
          }
        }
      }
      /*
      if (i < N - 1  && !removeIndexAll.contains(i + 1) && array[i][1] >= array[i + 1][0]) {
        totalSum -= array[i][1] - array[i + 1][0];
      }
      */
    }
    

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
    if (noWorkNeeded) {
      pw.println(totalSum);
    } else {
      long minCovered = 10000000;
      minCovered *= 100000;
      int beginningCovered = Math.min(array[0][1], array[1][1]) - array[0][0]; 
      int endCovered = array[N - 1][1] - Math.max(array[N - 1][0], array[N - 2][1]);
      minCovered = Math.min(beginningCovered, endCovered);
      //the problem is i dont check if the beginning or the end has the min covered
      for (int i = 1; i < N - 1; i++) {
        if (!removeIndexAll.contains(i)) {
          int covered = array[i][1] - array[i][0];
          if (array[i + 1][1] <= array[i][1]) {
            covered -= array[i + 1][1] - array[i + 1][0];
          } else if (array[i][1] >= array[i + 1][0]) {
            covered -= array[i][1] - array[i + 1][0];
          }
          if (array[i - 1][1] >= array[i][0]) {
            covered -= array[i - 1][1] - array[i][0];
          }
          if ((long)covered < minCovered) {
            minCovered = (long)covered;
          }
        }
      }
      pw.println(totalSum - minCovered); 
    }
    pw.close();
    
  }
}