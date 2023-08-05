import java.io.*;
import java.util.*;

/*
9
0 1
1 0
1 1
1 2
2 1
2 2
3 1
3 2
4 1
*/

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    
    int sideFactor = 500;
    int[][] array = new int[N][2]; 
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt() + sideFactor, s.nextInt() + sideFactor};
    }
    
    int[][] directions = new int[4][2];
    directions[0] = new int[]{1, 0};
    directions[1] = new int[]{-1, 0};
    directions[2] = new int[]{0, 1};
    directions[3] = new int[]{0, -1};
    //System.out.println("________________");
    boolean[][] grid = new boolean[1000 + 600][1000 + 600];
    int[][] surroundings = new int[1000 + 600][1000 + 600];
    int numAdded = 0;
    for (int i = 0; i < N; i++) {
      int xCoor = array[i][0];
      int yCoor = array[i][1];
      if (grid[xCoor][yCoor]) {
        //numAdded -= 1;
        numAdded = Math.max(numAdded - 1, 0);
        //do nothing because it has already been filled in
      } else {
        grid[xCoor][yCoor] = true;
        surroundings[xCoor + 1][yCoor] += 1;
        surroundings[xCoor - 1][yCoor] += 1;
        surroundings[xCoor][yCoor + 1] += 1;
        surroundings[xCoor][yCoor - 1] += 1;

        ArrayList<int[]> queue = new ArrayList<>();
        if (surroundings[xCoor][yCoor] == 3) {
          queue.add(new int[]{xCoor, yCoor});
        }
        for (int[] direction : directions) {
          if (surroundings[xCoor + direction[0]][yCoor + direction[1]] == 3 && grid[xCoor + direction[0]][yCoor + direction[1]]) {
            queue.add(new int[]{xCoor + direction[0], yCoor + direction[1]});
          }
        }
        /*
        if (surroundings[xCoor + 1][yCoor] == 3 && grid[xCoor + 1][yCoor]) {
          queue.add(new int[]{xCoor + 1, yCoor});
        }
        if (surroundings[xCoor - 1][yCoor] == 3 && grid[xCoor - 1][yCoor]) {
          queue.add(new int[]{xCoor - 1, yCoor});
        }
        if (surroundings[xCoor][yCoor + 1] == 3 && grid[xCoor][yCoor + 1]) {
          queue.add(new int[]{xCoor, yCoor + 1});
        }
        if (surroundings[xCoor][yCoor - 1] == 3 && grid[xCoor][yCoor - 1]) {
          queue.add(new int[]{xCoor, yCoor - 1});
        }
        */

        //BFS here (current is the coordinate that needs fixing)
        //boolean[][] visited = new boolean[5 + sideFactor * 2][5 + sideFactor * 2];
        //visited[xCoor][yCoor] = true;
        int queueIndex = 0;
        while (queueIndex < queue.size()) {
          int[] current = queue.get(queueIndex);
          queueIndex += 1;
          if (surroundings[current[0]][current[1]] == 3) {
            for (int[] direction : directions) {
              if (grid[current[0] + direction[0]][current[1] + direction[1]] == false) {
                //queue.add(new int[]{current[0] + direction[0], current[1] + direction[1]});
                if (surroundings[current[0] + direction[0]][current[1] + direction[1]] == 3) {
                  queue.add(new int[]{current[0] + direction[0], current[1] + direction[1]});
                }
                numAdded += 1;
                grid[current[0] + direction[0]][current[1] + direction[1]] = true;
                surroundings[current[0] + direction[0] + 1][current[1] + direction[1]] += 1;
                surroundings[current[0] + direction[0] - 1][current[1] + direction[1]] += 1;
                surroundings[current[0] + direction[0]][current[1] + direction[1] + 1] += 1;
                surroundings[current[0] + direction[0]][current[1] + direction[1] - 1] += 1;
                
                for (int[] directiona : directions) {
                  if (surroundings[current[0] + direction[0] + directiona[0]][current[1] + direction[1] + directiona[1]] == 3 && grid[current[0] + direction[0] + directiona[0]][current[1] + direction[1] + directiona[1]]) {
                    queue.add(new int[]{current[0] + direction[0] + directiona[0], current[1] + direction[1] + directiona[1]});
                  }
                }
                
              }
            }
            
          } else {
            //just look at the next item in the queue
          }
          
        }
      }
      System.out.println(numAdded);
    }
    /*
    for (boolean[] row : grid) {
      System.out.println(Arrays.toString(row));
    }

    */
  }
}
