import java.io.*;
import java.util.*;

class Main {  

  /* try combining smallest edges
  start with N clusters
  combine clusters until K clusters remain
  use union find to combine clusters/check if two things are connected
  
  primms without a priority queue
  */


  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("walk.in"));
    int N = s.nextInt();
    int K = s.nextInt();

    ArrayList<long[]> array = new ArrayList<>();
    for (int i = 1; i < N + 1; i++) {
      for (int j = i + 1; j < N + 1; j++) {
        array.add(new long[]{((long)2019201913 * (long)i + (long)2019201949 * (long)j) % (long)2019201997, i, j});
      }
    }
    System.out.println(array.size());

    Collections.sort(array, (long[] a, long[] b) -> {
      return (int)(a[0] - b[0]);
    });

    /*
    for (int i = 0; i < array.size(); i++) {
      System.out.println(array.get(i)[0] + " " + array.get(i)[1] + " " + array.get(i)[2]);
    }
    */

    System.out.println(array.size());

    UF unionFind = new UF(N);
    int ans = 0;
    boolean done = false;

    for (int i = 0; i < array.size(); i++) {
      if (!done) {
        unionFind.merge((int)array.get(i)[1], (int)array.get(i)[2]);
      }
      if (done) {
        if (!unionFind.isConnected((int)array.get(i)[1], (int)array.get(i)[2])) {
          ans = i;
          break;
        }
      }
      if (unionFind.numComponents == K) {
        done = true;
      }
    }
    

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("walk.out"))));
    pw.println(array.get(ans)[0]);
    pw.close();
  }
}

class UF {
  int[] parents;  
  int numComponents;

  public UF(int N) {
    parents = new int[N + 1];
    for (int i = 0; i < N + 1; i++) {
      parents[i] = i;
    }
    numComponents = N;
  }

  public void merge(int a, int b) {
    if (!isConnected(a, b)) {
      numComponents -= 1;
    }
    parents[getRoot(a)] = getRoot(b);
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