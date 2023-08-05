import java.io.*;
import java.util.*;

class newMain {  
  
  /*
  use a 1D array for UF
  Assign an integer to each point (multiply x-coordinate by 1000 and add y-coordinate or just assign it based on order)

  Use primms instead
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
    

    newUF unionFind = new newUF(N);
    int sum = 0;


    for (int i = 0; i < products.size(); i++) {
      
      if (!unionFind.isConnected(products.get(i)[1], products.get(i)[2])) {
        sum += products.get(i)[0];
        unionFind.merge(products.get(i)[1], products.get(i)[2]);
      }
      if (unionFind.numConnections == N - 1) {
        break;
      }
      
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("irrigation.out"))));
    pw.println(sum);
    pw.close();
  }
}


class newUF {
  int[] parents;
  int numConnections;
  int[] size;

  public newUF(int N) {
    parents = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      parents[i] = i;
      size[i] = 1;
    }
    numConnections = 0;
  }

  public void merge(int a, int b) {
    if (!isConnected(a, b)) {
      numConnections += 1;
    }
    int aRoot = getRoot(a);
    int bRoot = getRoot(b);
    if (size[aRoot] > size[bRoot]) {
      parents[bRoot] = aRoot;
    } else if (size[aRoot] <= size[bRoot]) {
      parents[aRoot] = bRoot;
      if (size[aRoot] <= size[bRoot]) {
        size[bRoot] += 1;
      }
    }
  }

  public int getRoot(int a) {
    if (parents[a] == a) {
      return a;
    } else {
      parents[a] = getRoot(parents[a]);
      return getRoot(parents[a]);
    }
  }

  public boolean isConnected(int a, int b) {
    return (getRoot(a) == getRoot(b));
  }
}