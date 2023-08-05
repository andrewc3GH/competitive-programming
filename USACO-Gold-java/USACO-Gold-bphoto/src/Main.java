import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("bphoto.in"));
    int N = s.nextInt();
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), i + 1};
    }
    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    FenwickTree t = new FenwickTree(N);

    int ans = 0;
    for (int i = N - 1; i > -1; i--) {
      int left = t.query(1, array[i][1]);
      int right = t.query(array[i][1], N);
      if (right * 2 < left || left * 2 < right) {
        ans += 1;
      }

      t.update(array[i][1], 1);
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("bphoto.out"))));
    pw.println(ans);
    pw.close();
  }
}

class FenwickTree {
  int length;
  int[] array;  

  public FenwickTree(int N) {
    length = N;
    array = new int[N + 1];
  }

  public FenwickTree(int N, int[] arr) {
    length = N;
    array = new int[N + 1];
    for (int i = 0; i < arr.length; i++) {
      update(i + 1, arr[i]);
    }
  }

  public void update(int index, int val) {
    while (index <= length) {
      array[index] += val;
      index += index & -index;
    }
  }

  public int sum(int index) {
    int sum = 0;
    while (index > 0) {
      sum += array[index];
      index -= index & -index;
    }
    return sum;
  }

  public int query(int startingIndex, int endingIndex) {
    int value = 0;
    value = sum(endingIndex) - sum(startingIndex - 1);
    return value;
  }
}