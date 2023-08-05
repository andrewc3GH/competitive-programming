import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    //Scanner s = new Scanner(new File("threesum.in"));
    BufferedReader br = new BufferedReader(new FileReader("threesum.in"));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());
    int[][] array = new int[N][2];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{Integer.parseInt(st.nextToken()), i};
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    //start, end
    long[][] ans = new long[N][N];

    for (int i = 0; i < N - 1; i++) {
      int index = N - 1;
      for (int j = i + 1; j < N; j++) {
        while (index > -1) {
          if (i != index && j != index) {
            if (array[i][0] + array[j][0] + array[index][0] > 0) {
              index -= 1;
              
            } else if (array[i][0] + array[j][0] + array[index][0] == 0) {
              int newIndex = index;
              //System.out.println("match");
              while (newIndex > -1) {
                if (i != newIndex && j != newIndex) {
                  if (array[i][0] + array[j][0] + array[newIndex][0] == 0) {
                    //System.out.println(array[i][0] + " " + array[j][0] + " " + array[newIndex][0]);
                    //System.out.println(Math.min(Math.min(array[j][1], array[i][1]), array[newIndex][1]) + " " + Math.max(Math.max(array[i][1], array[j][1]), array[newIndex][1]));
                    ans[Math.min(Math.min(array[j][1], array[i][1]), array[newIndex][1])][Math.max(Math.max(array[i][1], array[j][1]), array[newIndex][1])] += 1;
                    newIndex -= 1;
                  } else {
                    break;
                  }
                } else {
                  newIndex -= 1;
                }
              }
              break;
            } else {
              index = Math.min(index + 1, N - 1);
              break;
            }
          } else {
            index -= 1;
          }
        } 
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        ans[i][j] /= 3;
      }
    }
    //int[][] pSums = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N - 1; j++) {
        ans[i][j + 1] += ans[i][j];
      }
    }
    
    for (int i = N - 1; i > 0; i--) {
      for (int j = i + 1; j < N; j++) {
        ans[i - 1][j] += ans[i][j];
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("threesum.out"))));
    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      int b = Integer.parseInt(st.nextToken()) - 1;
      
      pw.println(ans[a][b]);
    }
    pw.close();
    br.close();
  }
}

/*
int maxNum = Math.max(a, b);
int minNum = Math.min(a, b);
long area = ans[maxNum][maxNum];
if (minNum > 0) {
  area += ans[minNum][minNum];
  area -= ans[minNum - 1][maxNum - 1];
  area -= ans[maxNum - 1][minNum - 1];
}
pw.println(area); (bottom left half of ans is always empty so area works)
*/