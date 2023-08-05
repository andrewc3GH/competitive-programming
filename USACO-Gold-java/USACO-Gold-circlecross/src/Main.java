import java.io.*;
import java.util.*;

class Main {  
  /*
  when encountering a new cow, update fenwick tree by 1 at that index
  keep track of the startingIndex of a cow
  when encountering a visited cow, upadate fenwick tree by -1 at the startingIndex
  query for the number of new cows in between the starting and ending index
  */
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("circlecross.in"));
    int N = s.nextInt();
    int[] firstIndex = new int[N];
    for (int i = 0; i < N; i++) {
      firstIndex[i] = -1;
    }

    int[] array = new int[2 * N];
    for (int i = 0; i < 2 * N; i++) {
      array[i] = s.nextInt();
    }

    int ans = 0;
    FenwickTree t = new FenwickTree(2 * N);
    for (int i = 0; i < 2 * N; i++) {
      if (firstIndex[array[i] - 1] == -1) {
        t.update(i + 1, 1);
        firstIndex[array[i] - 1] = i + 1;
      } else {
        t.update(firstIndex[array[i] - 1], -1);
        ans += t.query(firstIndex[array[i] - 1], i);
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("circlecross.out"))));
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