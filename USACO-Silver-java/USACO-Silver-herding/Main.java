import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("herding.in"));
    int N = s.nextInt();
    int[] cowArray = new int[N];
    for (int i = 0; i < N; i++) {
      cowArray[i] = s.nextInt();
    }
    Arrays.sort(cowArray);
    System.out.println(Arrays.toString(cowArray));
    
    int maxNum = 0;
    for (int i = 0; i < N; i++) {
      int minValue = i;
      int maxValue = N;
      int currentValue = -2;
      int oldValue = -1;
      while (currentValue != N && oldValue != currentValue) {
        oldValue = currentValue;
        currentValue = (minValue + maxValue)/2;
        if (cowArray[i] >= cowArray[currentValue] - N + 1) {
          minValue = currentValue;
        } else {
          maxValue = currentValue;
        }
      }
      if (currentValue - i + 1 > maxNum) {
        maxNum = currentValue - i + 1;
      }
    }

    int minNum = 0;
    if ((cowArray[N - 1] - cowArray[1] == N - 2 && cowArray[1] - cowArray[0] > 2) || (cowArray[N - 2] - cowArray[0] == N - 2 && cowArray[N - 1] - cowArray[N - 2] > 2)) {
      minNum = 2;
    } else {
      minNum = N - maxNum;
    }
    System.out.println(minNum);

    int maxVal = 0;
    if (cowArray[1] - cowArray[0] > cowArray[N - 1] - cowArray[N - 2]) {
      //right side works
      for (int i = N - 3; i > -1; i--) {
        maxVal += Math.abs(cowArray[i] - cowArray[i + 1]) - 1;
      }
    } else {
      //left side works
      for (int i = 2; i < N; i++) {
        maxVal += cowArray[i] - cowArray[i - 1] - 1;
      }
    }
    System.out.println(maxVal);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
    pw.println(minNum);
    pw.println(maxVal);
    pw.close();
  }
}

/*
[1, 3, 5, 7, 12]
3, 4, 5, 7, 12
4, 5, 6, 7, 12
5, 6, 7, 8, 12
6, 7, 8, 9, 12
7, 8, 9, 10, 12
8, 9, 10, 11, 12

[1, 3, 5, 7]
3, 4, 5, 7
4, 5, 6, 7



for paintbarn, (pretend N is 1000)
create list of x-events (when to add y-interval of paint)
sort the list of x-events
loop through the list of x-events
maintain the number of layers of paint on each square on the current column (up - down)
update the answer appropriately
(should get about half the test cases)
(january 2019 too)
*/