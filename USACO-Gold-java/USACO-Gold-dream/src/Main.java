import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("dream.in"));
    int N = s.nextInt();
    int M = s.nextInt();

    int[][] array = new int[N + 2][M + 2];

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < M + 1; j++) {
        array[i][j] = s.nextInt();
      }
    }

    //N by M by 2 (plain, orange)
    boolean[][][] visited = new boolean[N + 2][M + 2][2];

    //0 impassible, 1 normal, 2 orange, 3 blue, 4 purple
    PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> {
      return a[2] - b[2];
    });

    //y, x, numsteps, orange (no 0, yes 1)
    queue.add(new int[]{1, 1, 0, 0});

    //up, right, down, left
    int[][] directionArray = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; 
    
    int ans = -1;
    while (queue.size() > 0) {
      int[] node = queue.poll();
      int[] location = new int[]{node[0], node[1]};
      int numSteps = node[2];
      boolean isOrange = node[3] == 1;
      if (location[0] == N && location[1] == M) {
        ans = numSteps;
        break;
      }
      //System.out.println(Arrays.toString(location) + " -> " + array[location[0]][location[1]]);

      //make condition for if purple

      for (int i = 0; i < 4; i++) {
        int[] newLocation = new int[]{location[0] + directionArray[i][0], location[1] + directionArray[i][1]};
        int val = array[newLocation[0]][newLocation[1]];
        if (isOrange && !visited[newLocation[0]][newLocation[1]][1]) {
          visited[newLocation[0]][newLocation[1]][1] = true;
          if (val == 1 || val == 3) {
            //add to queue
            queue.add(new int[]{newLocation[0], newLocation[1], numSteps + 1, 1});
          } else if (val == 0) {
            //impassible
          } else if (val == 2) {
            //add to queue but now orange
            queue.add(new int[]{newLocation[0], newLocation[1], numSteps + 1, 1});
          } else if (val == 4) {
            visited[newLocation[0]][newLocation[1]][1] = false;
            int steps = 1;
            while (true) {
              steps += 1;
              newLocation = new int[]{newLocation[0] + directionArray[i][0], newLocation[1] + directionArray[i][1]};
              val = array[newLocation[0]][newLocation[1]];
              if (val == 1) {
                //add to queue
                queue.add(new int[]{newLocation[0], newLocation[1], numSteps + steps, 0});
                visited[newLocation[0]][newLocation[1]][0] = true;
                break;
              } else if (val == 0 || val == 3) {
                //add previous to queue;
                queue.add(new int[]{newLocation[0] - directionArray[i][0], newLocation[1] - directionArray[i][1], numSteps + steps - 1, 0});
                break;
              } else if (val == 2) {
                queue.add(new int[]{newLocation[0], newLocation[1], numSteps + steps, 1});
                visited[newLocation[0]][newLocation[1]][1] = true;
                break;
              }
            }
          }
        } else if (!visited[newLocation[0]][newLocation[1]][0]) {
          visited[newLocation[0]][newLocation[1]][0] = true;
          if (val == 1) {
            //add to queue
            queue.add(new int[]{newLocation[0], newLocation[1], numSteps + 1, 0});
          } else if (val == 0 || val == 3) {
            //impassible
          } else if (val == 2) {
            //add to queue but now orange
            queue.add(new int[]{newLocation[0], newLocation[1], numSteps + 1, 1});
          } else if (val == 4) {
            visited[newLocation[0]][newLocation[1]][0] = false;
            int steps = 1;
            while (true) {
              steps += 1;
              newLocation = new int[]{newLocation[0] + directionArray[i][0], newLocation[1] + directionArray[i][1]};
              val = array[newLocation[0]][newLocation[1]];
              if (val == 1) {
                //add to queue
                queue.add(new int[]{newLocation[0], newLocation[1], numSteps + steps, 0});
                break;
              } else if (val == 0 || val == 3) {
                //add previous to queue;
                queue.add(new int[]{newLocation[0] - directionArray[i][0], newLocation[1] - directionArray[i][1], numSteps + steps - 1, 0});
                visited[newLocation[0]][newLocation[1]][0] = true;
                break;
              } else if (val == 2) {
                queue.add(new int[]{newLocation[0], newLocation[1], numSteps + steps, 1});
                visited[newLocation[0]][newLocation[1]][1] = true;
                break;
              }
            }
          }
        }
      }


    }

    //address purple nodes -> turn queue into priority queue (with distance travelled)
    //do not put purple squares in visited
    //when u reach a purple square, slide through it
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("dream.out"))));
    pw.println(ans);
    pw.close();
  }
}