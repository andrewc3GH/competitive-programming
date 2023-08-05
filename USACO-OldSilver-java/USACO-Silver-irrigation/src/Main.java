import java.io.*;
import java.util.*;

class Main {  
  
  /*
  use a 1D array for UF
  Assign an integer to each point (multiply x-coordinate by 1000 and add y-coordinate or just assign it based on order)
  */

  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("irrigation.in"));
    int N = s.nextInt();
    int C = s.nextInt();

    int[][] array = new int[N][2];

    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    ArrayList<int[]> products = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        int val = (int)Math.pow(array[i][0] - array[j][0], 2) + (int)Math.pow(array[i][1] - array[j][1], 2);
        if (val >= C) {
          products.add(new int[]{val, i, j});
        }
      }
    }

    Collections.sort(products, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    /*
    for (int i = 0; i < products.size(); i++) {
      System.out.println(Arrays.toString(products.get(i)));
    }
    */

    UF unionFind = new UF(N);
    int sum = 0;

    for (int i = 0; i < products.size(); i++) {
      if (!unionFind.isConnected(array[products.get(i)[1]], array[products.get(i)[2]])) {
        sum += products.get(i)[0];
      }
      unionFind.merge(array[products.get(i)[1]], array[products.get(i)[2]]);

      if (unionFind.numConnections == N - 1) {
        break;
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("irrigation.out"))));
    pw.println(sum);
    pw.close();
  }
}


class UF {
  int[][][] parents;
  int numConnections;
  int[][] size;

  public UF(int N) {
    parents = new int[1000 + 1][1000 + 1][2];
    size = new int[1000 + 1][1000 + 1];
    for (int i = 0; i < 1000 + 1; i++) {
      for (int j = 0; j < 1000 + 1; j++) {
        parents[i][j] = new int[]{i, j};
        size[i][j] = 1;
      }
    }
    numConnections = 0;
  }

  public void merge(int[] a, int[] b) {
    if (!isConnected(a, b)) {
      numConnections += 1;
    }
    int[] rootA = getRoot(a);
    int[] rootB = getRoot(b);
    if (size[rootA[0]][rootA[1]] > size[rootB[0]][rootB[1]]) {
      parents[rootB[0]][rootB[1]][0] = rootA[0];
      parents[rootB[0]][rootB[1]][1] = rootA[1];
    } else {
      parents[rootA[0]][rootA[1]][0] = rootB[0];
      parents[rootA[0]][rootA[1]][1] = rootB[1];
      if (size[rootA[0]][rootA[1]] == size[rootB[0]][rootB[1]]) {
        size[rootB[0]][rootB[1]] += 1;
      }
    }
  }

  public int[] getRoot(int[] a) {
    if (parents[a[0]][a[1]][0] == a[0] && parents[a[0]][a[1]][1] == a[1]) {
      return a;
    } else {
      int[] arr = getRoot(parents[a[0]][a[1]]);
      parents[a[0]][a[1]][0] = arr[0];
      parents[a[0]][a[1]][1] = arr[1];
      return arr;
    }
  }

  public boolean isConnected(int[] a, int[] b) {
    int[] rootA = getRoot(parents[a[0]][a[1]]);
    int[] rootB = getRoot(parents[b[0]][b[1]]);
    return (rootA[0] == rootB[0] && rootA[1] == rootB[1]);
  }
}