import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();

    //East is 0, North is 1

    //direction, x-coor, y-coor
    int[][] array = new int[N][3];

    for (int i = 0; i < N; i++) {
      if (s.next().equals("E")) {
        array[i][0] = 0;
      } else {
        array[i][0] = 1;
      }
      array[i][1] = s.nextInt();
      array[i][2] = s.nextInt();
      //System.out.println(Arrays.toString(array[i]));
    }
    //System.out.println();

    int[] collidedPosition = new int[N];
    HashMap<Integer, ArrayList<Integer>> collisions = new HashMap<>();
    int[] answer = new int[N];

    for (int i = 0; i < N; i++) {
      collidedPosition[i] = -1;
      collisions.put(i, new ArrayList<Integer>());
    }

    ArrayList<int[]> events = new ArrayList<>();

    for (int i = 0; i < N - 1; i++) {
      for (int j = i + 1; j < N; j++) {
        if (array[i][0] != array[j][0]) {
          //if they are perpendicular

          if (array[i][0] == 0) {
            //first one going east

            if (array[j][1] > array[i][1] && array[j][2] < array[i][2]) {
              int time = Math.max(array[j][1] - array[i][1], array[i][2] - array[j][2]);
              events.add(new int[]{i, j, time});
            }
          } else {
            //first one going north
            if (array[j][1] < array[i][1] && array[j][2] > array[i][2]) {
              int time = Math.max(array[i][1] - array[j][1], array[j][2] - array[i][2]);
              events.add(new int[]{i, j, time});
            }
          }
        }
      }
    }
    Collections.sort(events, (int[] a, int[] b) -> {
      return a[2] - b[2];
    });
    for (int[] event : events) {
      //System.out.println(Arrays.toString(event));
    }

    for (int[] event : events) {
      int i = event[0];
      int j = event[1];

      if (array[i][0] == 0) {
        //first one going east

        if (array[j][1] > array[i][1] && array[j][2] < array[i][2]) {
          int x = array[j][1] - array[i][1];
          int y = array[i][2] - array[j][2];
          if (y > x && (collidedPosition[i] == -1 || collidedPosition[i] > array[j][1]) && (collidedPosition[j] == -1 || collidedPosition[j] > array[i][2])) {
            collisions.get(i).add(j);
            collidedPosition[j] = array[i][2];
          } else if (y < x && (collidedPosition[i] == -1 || collidedPosition[i] > array[j][1]) && (collidedPosition[j] == -1 || collidedPosition[j] > array[i][2])) {
            collisions.get(j).add(i);
            collidedPosition[i] = array[j][1];
          }
        }
      } else {
        //first one going north
        if (array[j][1] < array[i][1] && array[j][2] > array[i][2]) {
          int x = array[i][1] - array[j][1];
          int y = array[j][2] - array[i][2];
          if (y > x && (collidedPosition[j] == -1 || collidedPosition[j] > array[i][1]) && (collidedPosition[i] == -1 || collidedPosition[i] > array[j][2])) {
            collisions.get(j).add(i);
            collidedPosition[i] = array[j][2];
          } else if (y < x && (collidedPosition[j] == -1 || collidedPosition[j] > array[i][1]) && (collidedPosition[i] == -1 || collidedPosition[i] > array[j][2])) {
            collisions.get(i).add(j);
            collidedPosition[j] = array[i][1];
          }
        }
      }
    }
    //System.out.println();  
    for (int i = 0; i < N; i++) {
      ArrayList<Integer> queue = new ArrayList<>();
      queue.add(i);
      int queueIndex = 0;
      int counter = 0;
      while (queueIndex < queue.size()) {
        int current = queue.get(queueIndex);
        queueIndex += 1;
        for (int neighbor : collisions.get(current)) {
          queue.add(neighbor);
          counter += 1;
        }
      }
      answer[i] = counter;
    }
    //System.out.println(Arrays.toString(answer));
    for (int i = 0; i < N; i++) {
      System.out.println(answer[i]);
    }
  }
}