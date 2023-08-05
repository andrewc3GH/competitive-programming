import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream("cownav.in");
    FastScanner s = new FastScanner(is);
    
    int N = s.nextInt();
    char[][] array = new char[N][N];
    //i is y, j is x
    for (int i = N - 1; i > -1; i--) {
      String temp = s.next();
      for (int j = 0; j < N; j++) {
        array[j][i] = temp.charAt(j);
      }
    }

    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    //0 = up, 1 = right, 2 = down, 3 = left
    int[] first = new int[]{0, 0, 0, 0, 0, 1};
    //arraydeque
    ArrayList<int[]> queue = new ArrayList<>();
    int queueIndex = 0;
    queue.add(first);

    int[][][][][][] minSteps = new int[N][N][4][N][N][4];
    minSteps[first[0]][first[1]][first[2]][first[3]][first[4]][first[5]] = Integer.MIN_VALUE;

    while (queueIndex < queue.size()) {
      int[] current = queue.get(queueIndex);
      int currentDist = minSteps[current[0]][current[1]][current[2]][current[3]][current[4]][current[5]] + 1;
      queueIndex += 1;
      //forward
      //check if at edge, check if haybale, check destination
      
      int[] forward = Arrays.copyOf(current, 6);
      int[] destination1 = {forward[0] + directions[forward[2]][0], forward[1] + directions[forward[2]][1], forward[2]};
      if (destination1[0] >= N || destination1[0] < 0 || destination1[1] >= N || destination1[1] < 0 || array[destination1[0]][destination1[1]] == 'H' || (forward[0] == N - 1 && forward[1] == N - 1)) {
        destination1 = new int[]{forward[0], forward[1], forward[2]};
      }

      int[] destination2 = {forward[3] + directions[forward[5]][0], forward[4] + directions[forward[5]][1], forward[5]};
      if (destination2[0] >= N || destination2[0] < 0 || destination2[1] >= N || destination2[1] < 0 || array[destination2[0]][destination2[1]] == 'H' || (forward[3] == N - 1 && forward[4] == N - 1)) {
        destination2 = new int[]{forward[3], forward[4], forward[5]};
      }

      forward = new int[]{destination1[0], destination1[1], destination1[2], destination2[0], destination2[1], destination2[2]};
      if (minSteps[forward[0]][forward[1]][forward[2]][forward[3]][forward[4]][forward[5]] == 0) {
        minSteps[forward[0]][forward[1]][forward[2]][forward[3]][forward[4]][forward[5]] = currentDist;
        queue.add(forward);
      }

      //left
      int[] toTheLeft = new int[]{current[0], current[1], (current[2] - 1 + 4)%4, current[3], current[4], (current[5] - 1 + 4)%4};
      if (minSteps[toTheLeft[0]][toTheLeft[1]][toTheLeft[2]][toTheLeft[3]][toTheLeft[4]][toTheLeft[5]] == 0) {
        minSteps[toTheLeft[0]][toTheLeft[1]][toTheLeft[2]][toTheLeft[3]][toTheLeft[4]][toTheLeft[5]] = currentDist;
        queue.add(toTheLeft);
      }
      //right
      int[] toTheRight = new int[]{current[0], current[1], (current[2] + 1)%4, current[3], current[4], (current[5] + 1)%4};
      if (minSteps[toTheRight[0]][toTheRight[1]][toTheRight[2]][toTheRight[3]][toTheRight[4]][toTheRight[5]] == 0) {
        minSteps[toTheRight[0]][toTheRight[1]][toTheRight[2]][toTheRight[3]][toTheRight[4]][toTheRight[5]] = currentDist;
        queue.add(toTheRight);
      }
    }
    int minVal = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        //System.out.println(minSteps[N - 1][N - 1][i][N - 1][N - 1][j] + " " + minVal);
        minVal = Math.min(minVal, minSteps[N - 1][N - 1][i][N - 1][N - 1][j]);
      }
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
    pw.println(minVal - Integer.MIN_VALUE);
    pw.close();
  }

  static class FastScanner { 
    public BufferedReader br; 
    public StringTokenizer st; 
  
    public FastScanner(InputStream is) throws IOException { 
      br = new BufferedReader(new InputStreamReader(is),32768);
      st = null;
    }
  
    public String next() { 
      while (st == null || !st.hasMoreTokens()) { 
        try { 
          st = new StringTokenizer(br.readLine()); 
        } 
        catch (IOException  e) { 
          throw new RuntimeException(e);
        }
      } 
      return st.nextToken(); 
    } 
  
    public int nextInt() { 
      return Integer.parseInt(next()); 
    } 
  
    public long nextLong() { 
      return Long.parseLong(next()); 
    } 
  
    public double nextDouble() { 
      return Double.parseDouble(next()); 
    } 
  
    public String nextLine() { 
      String str = ""; 
      try { 
        str = br.readLine(); 
      } catch (IOException e) { 
        throw new RuntimeException(e);
      } 
      return str; 
    }
  }
}