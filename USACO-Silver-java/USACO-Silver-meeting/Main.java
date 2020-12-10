import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("meeting.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int[][] roads = new int[M][4];
    for (int i = 0; i < M; i++) {
      roads[i] = new int[]{s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()};
    }
    
    /*
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    for (int i = 1; i < N + 1; i++) {
      map.put(i, new ArrayList<>());
    }
    */

    int[][][] arr = new int[M + 1][M + 1][2];
    for (int i = 0; i < M; i++) {
      arr[roads[i][0]][roads[i][1]] = new int[]{roads[i][2], roads[i][3]};
      //map.get(roads[i][0]).add(roads[i][1]);
    }

    /*for (int i = 0; i < N + 1; i++) {
      for (int j = 0; j < N + 1; j++) {
        System.out.print(Arrays.toString(arr[i][j]) + " ");
      }
      System.out.println();
    }*/

    HashSet<Integer>[] bLst = new HashSet[N + 1];
    for (int i = 0; i < N + 1; i++) {
      bLst[i] = new HashSet<Integer>();
    }
    bLst[1].add(0);
    HashSet<Integer>[] eLst = new HashSet[N + 1];
    for (int i = 0; i < N + 1; i++) {
      eLst[i] = new HashSet<Integer>();
    }
    eLst[1].add(0);
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        if (arr[i][j][0] != 0) {
          for (int a : bLst[i]) {
            bLst[j].add(a + arr[i][j][0]);
          }
          for (int a : eLst[i]) {
            eLst[j].add(a + arr[i][j][1]);
          }
        }
      }
    }

    int ans = 2147483647;
    for (int a : bLst[N]) {
      if (eLst[N].contains(a)) {
        ans = Math.min(ans, a);
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
    pw.println(ans);
    pw.close();
  }
}

/*
Each problem set has varying difficulty (1 is easy, 15 is mid gold (good luck))
https://teamscode.com/assets/docs/spring_2018_cgs/problem_set.pdf
https://teamscode.com/assets/docs/fall_2018_cgs/intermediate_problem_set.pdf 
https://teamscode.com/assets/docs/fall_2018_cgs/advanced_problem_set.pdf
*/