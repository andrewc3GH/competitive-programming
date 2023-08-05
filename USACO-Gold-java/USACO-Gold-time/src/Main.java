import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("time.in"));
    int N = s.nextInt(); //cities
    int M = s.nextInt(); //roads
    int C = s.nextInt(); //cost
    
    int[] cost = new int[N];
    for (int i = 0; i < N; i++) {
      cost[i] = s.nextInt();
    }

    ArrayList[] connections = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      connections[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < M; i++) {
      connections[s.nextInt() - 1].add(s.nextInt() - 1);
    }
    //System.out.println(Arrays.toString(connections));

    int[] most = new int[N];
    for (int i = 0; i < N; i++) {
      most[i] = Integer.MIN_VALUE;
    }
    most[0] = 0;

    ArrayList<int[]> queue = new ArrayList<>();
    queue.add(new int[]{0, 0});
    int queueIndex = 0;

    ArrayList<int[]> options = new ArrayList<>();

    while (queueIndex < queue.size()) {
      int city = queue.get(queueIndex)[0];
      int steps = queue.get(queueIndex)[1];
      queueIndex += 1;
      if (queue.size() == 1 || city != 0) {
        for (int i = 0; i < connections[city].size(); i++) {
          int neighbor = (int)connections[city].get(i);
          int realVal = most[city] + cost[neighbor];
          int newVal = realVal - C * (steps + 1) * (steps + 1) + C * steps * steps;
          //System.out.println(newVal + " " + most[neighbor]);
          if (newVal > most[neighbor]) {
            //newVal += C * (steps) * (steps);
            most[neighbor] = newVal;
            queue.add(new int[]{neighbor, steps + 1});
            if (neighbor == 0) {
              options.add(new int[]{most[city] + cost[neighbor] + C * (steps) * (steps), steps + 1});
            }
          }
        }
      }
    }
    int[] dp = new int[2000];
    int ans = 0;
    for (int i = 0; i < options.size(); i++) {
      //System.out.println(Arrays.toString(options.get(i)));
      dp[options.get(i)[1]] = Math.max(dp[options.get(i)[1]], options.get(i)[0]);
      /*
      int maxVal = 0;
      for (int j = 1; j < N + N; j++) {
        int newVal = options.get(i)[0] * j - C * (options.get(i)[1] * j) * (options.get(i)[1] * j);
        if (newVal > maxVal) {
          maxVal = newVal;
        } else {
          ans = Math.max(ans, maxVal);
          break;
        }
      }*/
    }   
    
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < i + 1; j++) {
        dp[i + j] = Math.max(dp[i + j], dp[i] + dp[j]);
      }
    }
    /*for (int i = 0; i < 10; i++) {
      System.out.print(dp[i] + " ");
    }*/
    for (int i = 0; i < 1000; i++) {
      ans = Math.max(ans, dp[i] - C * i * i);
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("time.out"))));
    pw.println(ans);
    pw.close();
  }
}