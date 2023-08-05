import java.io.*;
import java.util.*;

/*

3
57 120 87
200 100 150
2 141 135

*/

class NewMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();

    int[][] array = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        array[i][j] = s.nextInt();
      }
    }

    int[][] horizontal = new int[N][N];
    //initialize less than 100 psum
    for (int i = 0; i < N; i++) {
      if (array[i][0] < 100) {
        horizontal[i][0] = 1;
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 1; j < N; j++) {
        horizontal[i][j] = horizontal[i][j - 1];
        if (array[i][j] < 100) {
          horizontal[i][j] += 1;
        }
      }
    }
    for (int i = 1; i < N; i++) {
      for (int j = 0; j < N; j++) {
        horizontal[i][j] += horizontal[i - 1][j];
      }
    }

    int[][] hundred = new int[N][N];
    //initialize exactly 100 psum
    for (int i = 0; i < N; i++) {
      if (array[i][0] == 100) {
        hundred[i][0] = 1;
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 1; j < N; j++) {
        hundred[i][j] = hundred[i][j - 1];
        if (array[i][j] == 100) {
          hundred[i][j] += 1;
        }
      }
    }
    for (int i = 1; i < N; i++) {
      for (int j = 0; j < N; j++) {
        hundred[i][j] += hundred[i - 1][j];
      }
    }

    long counter = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int width = 0; width < N; width++) {
          for (int height = 0; height < N; height++) {
            if (j + height < N && i + width < N) {
              int numHundreds = hundred[j + height][i + width];
              if (j > 0 && i > 0) {
                numHundreds += hundred[j - 1][i - 1];
              } if (j > 0) {
                numHundreds -= hundred[j - 1][i + width];
              } if (i > 0) {
                numHundreds -= hundred[j + height][i - 1];
              }
              int numLess = horizontal[j + height][i + width];
              if (j > 0 && i > 0) {
                numLess += horizontal[j - 1][i - 1];
              } if (j > 0) {
                numLess -= horizontal[j - 1][i + width];
              } if (i > 0) {
                numLess -= horizontal[j + height][i - 1];
              }
              if (numHundreds > 0 && numLess == 0) {
                counter += 1;
              } else if (numLess > 0) {
                break;
              }
            } else {
              break;
            }
          }
        }
      }
    }
    System.out.println(counter);
  }
}
