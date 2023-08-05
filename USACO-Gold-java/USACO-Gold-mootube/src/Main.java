import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("mootube.in"));
    int N = s.nextInt();
    int Q = s.nextInt();
    int[][] array = new int[N - 1][4];
    for (int i = 0; i < N - 1; i++) {
      array[i] = new int[]{s.nextInt() - 1, s.nextInt() - 1, s.nextInt(), i};
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return b[2] - a[2];
    });

    UF unionFind = new UF(N);

    int[][] qLst = new int[Q][3];
    for (int i = 0; i < Q; i++) {
      int a = s.nextInt();
      int b = s.nextInt() - 1;
      qLst[i] = new int[]{b, a, i};
    }
    Arrays.sort(qLst, (int[] a, int[] b) -> {
      return b[1] - a[1];
    });

    
    for (int i = 0; i < Q; i++){
      //System.out.println(Arrays.toString(qLst[i]));
    }

    int[] ans = new int[Q];
    int index = 0;
    int qIndex = 0;
    while (index < N - 1 && qIndex < Q) {
      //System.out.println(index + " " + qIndex + " " + array[index][2] + " " + qLst[qIndex][1]);
      int minR = qLst[qIndex][1];
      if (array[index][2] >= minR) {
        //System.out.println(array[index][0] + " " + array[index][1]);
        unionFind.merge(array[index][0], array[index][1]);
        index += 1;
      } else {
        //check query here
        ans[qLst[qIndex][2]] = (unionFind.size[unionFind.getRoot(qLst[qIndex][0])] - 1);
        qIndex += 1;
      }
    }
    for (int i = qIndex; i < Q; i++) {
      ans[qLst[i][2]] = (unionFind.size[unionFind.getRoot(qLst[i][0])] - 1);
    }
    //System.out.println(Arrays.toString(ans));
    
    //use UF
    //sort quieries by highest
    //as u go through quieries, expand UF tree
    //list of events to add edges

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("mootube.out"))));
    for (int i = 0; i < Q; i++) {
      pw.println(ans[i]);
    }
    pw.close();
  }
}

class UF {
  int[] parents;
  int[] size;

  public UF(int N) {
    size = new int[N];
    parents = new int[N];
    for (int i = 0; i < N; i++) {
      parents[i] = i;
      size[i] = 1;
    }
  }

  public int getRoot(int a) {
    if (parents[a] == a) {
      return a;
    }
    parents[a] = getRoot(parents[a]);
    return parents[a];
  }

  public void merge(int a, int b) {
    if (!isConnected(a, b)) {
      size[getRoot(b)] += size[getRoot(a)];
      parents[getRoot(a)] = getRoot(b);
    }
  }
  
  public boolean isConnected(int a, int b) {
    return getRoot(a) == getRoot(b);
  }
}