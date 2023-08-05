import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("pump.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int[][] array = new int[M][4];
    for (int i = 0; i < M; i++) {
        array[i] = new int[]{s.nextInt() - 1, s.nextInt() - 1, s.nextInt(), s.nextInt()};
    }
    
    Arrays.sort(array, (int[] a, int[] b) -> {
        if (b[3] != a[3]) {
            return b[3] - a[3];
        } else {
            return a[2] - b[2];
        }
    });
    
    ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      connections.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < M; i++) {
        connections.get(array[i][0]).add(i);
        connections.get(array[i][1]).add(i);
    }
    
    int bestAns = 0;
    boolean[] usable = new boolean[M];
    
    for (int i = 0; i < M; i++) {
        //shortest path algo
        
        int flowRate = array[i][3];
        usable[i] = true;
        
        //location, cost
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> {
            return a[1] - b[1];
        });
        queue.add(new int[]{0, 0});
        
        boolean[] visited = new boolean[M];
        
        int ans = Integer.MAX_VALUE;
        
        while (queue.size() > 0) {
            int[] current = queue.poll();
            if (current[0] == N - 1) {
              //System.out.println("here");
                ans = Math.min(ans, current[1]);
            }
            for (int j = 0; j < connections.get(current[0]).size(); j++) {
                int connection = (int)connections.get(current[0]).get(j);
                if (!visited[connection] && usable[connection]) {
                    visited[connection] = true;
                    if (array[connection][0] != current[0]) {
                        queue.add(new int[]{array[connection][0], current[1] + array[connection][2]}); 
                    } else {
                        queue.add(new int[]{array[connection][1], current[1] + array[connection][2]}); 
                    }
                    
                }
            }
        }
        //System.out.println(ans);
        //System.out.println(ans + " " + flowRate);
        bestAns = Math.max(bestAns, flowRate * 10*10*10*10*10*10 / ans);
    }
    
    
 
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("pump.out"))));
    pw.println(bestAns);
    pw.close();
  }
}
