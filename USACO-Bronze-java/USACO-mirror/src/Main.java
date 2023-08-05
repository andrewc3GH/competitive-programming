import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(new File("mirror.in"));
        int N = s.nextInt();
        int M = s.nextInt();

        //from left, from top, from right, from down
        //0, 1, 2, 3
        int[][][] array = new int[N][M][4];
        for (int i = 0; i < N; i++) {
            String line = s.next();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '/') {
                    array[i][j] = new int[]{3, 2, 1, 0};
                } else {
                    array[i][j] = new int[]{1, 0, 3, 2};
                }
            }
        }
        int maxCounter = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < M; j++) {
                int direction = -1;
                int[] location = new int[]{j, 0};
                int counter = 0;
                if (i == 0) {
                    //going down
                    direction = 1;
                    location = new int[]{j, -1};
                } else {
                    //going up
                    direction = 3;
                    location = new int[]{j, N};
                }
                while (true) {
                    counter += 1;
                    if (direction == 0) {
                        location[0] += 1;
                    } else if (direction == 1) {
                        location[1] += 1;
                    } else if (direction == 2) {
                        location[0] -= 1;
                    } else {
                        location[1] -= 1;
                    }
                    if (location[0] < 0 || location[0] >= M || location[1] < 0 || location[1] >= N) {
                        break;
                    }
                    direction = array[location[1]][location[0]][direction];
                }
                maxCounter = Math.max(counter, maxCounter);
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                int direction = -1;
                int counter = 0;
                int[] location = new int[]{0, j};
                if (i == 0) {
                    //going right
                    direction = 0;
                    location = new int[]{-1, j};
                } else {
                    //going left
                    direction = 2;
                    location = new int[]{M, j};
                }
                
                while (true) {
                    counter += 1;
                    if (direction == 0) {
                        location[0] += 1;
                    } else if (direction == 1) {
                        location[1] += 1;
                    } else if (direction == 2) {
                        location[0] -= 1;
                    } else {
                        location[1] -= 1;
                    }
                    if (location[0] < 0 || location[0] >= M || location[1] < 0 || location[1] >= N) {
                        break;
                    }
                    direction = array[location[1]][location[0]][direction];
                }
                maxCounter = Math.max(counter, maxCounter);
            }
        }
        

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mirror.out")));
        pw.println(maxCounter - 1);
        pw.close();

    }
}