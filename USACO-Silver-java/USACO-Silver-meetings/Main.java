import java.io.*;
import java.util.*;

//sort cows by position
//for each interval (cow position -> cow position + (T * 2)), binary search for the endpoints of the set of cows going the opposite direction

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("meetings.in"));
    int N = s.nextInt();
    int L = s.nextInt();
    //weight, position, direction
    int[][] cowArray = new int[N][3];
    for (int i = 0; i < N; i++) {
      int[] temp = new int[3];
      temp[0] = s.nextInt();
      temp[1] = s.nextInt();
      temp[2] = s.nextInt();
      cowArray[i] = temp;
    }
    Arrays.sort(cowArray, (int[] a, int[] b) -> {
      return a[1] - b[1];
    });

    ArrayList<int[]> goingLeft = new ArrayList<>();
    for (int[] item : cowArray) {
      if (item[2] == 1) {
        goingLeft.add(item);
      }
    }
    ArrayList<int[]> goingRight = new ArrayList<>();
    for (int[] item : cowArray) {
      if (item[2] == -1) {
        goingRight.add(item);
      }
    }

    int numNegative = 0;
    int numPositive = 0;
    ArrayList<Integer> leftArray = new ArrayList<>();
    ArrayList<Integer> rightArray = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      if (cowArray[i][2] == 1) {
        numPositive += 1;
        rightArray.add(L - cowArray[i][1]);
      } else {
        numNegative += 1;
        leftArray.add(cowArray[i][1]);
      }
    }
    Collections.sort(rightArray);
    Collections.sort(leftArray);

    ArrayList<Integer> leftWeights = new ArrayList<>();
    ArrayList<Integer> rightWeights = new ArrayList<>();
    for (int i = 0; i < numNegative; i++) {
      leftWeights.add(cowArray[i][0]);
    }
    for (int i = N - 1; i > numNegative - 1; i--) {
      rightWeights.add(cowArray[i][0]);
    }

    double halfWay = 0;
    for (int i = 0; i < N; i++) {
      halfWay += cowArray[i][0];
    }
    halfWay /= 2;

    /*
    System.out.println(leftArray + "  " + rightArray);
    System.out.println(leftWeights + "  " + rightWeights);
    */

    int currentWeight = 0;
    int leftIndex = 0;
    int rightIndex = 0;
    int T = 0;
    while (true) {
      if (rightIndex >= rightArray.size()) {
        currentWeight += leftWeights.get(leftIndex);
        if ((double)currentWeight >= halfWay) {
          T = leftArray.get(leftIndex);
          break;
        }
        leftIndex += 1;
      } else if (leftIndex >= leftArray.size()) {
        currentWeight += rightWeights.get(rightIndex);
        if ((double)currentWeight >= halfWay) {
          T = rightArray.get(rightIndex);
          break;
        }
        rightIndex += 1;
      }
      if (rightArray.get(rightIndex) < leftArray.get(leftIndex)) {
        currentWeight += rightWeights.get(rightIndex);
        if ((double)currentWeight >= halfWay) {
          T = rightArray.get(rightIndex);
          break;
        }
        rightIndex += 1;
      } else if (rightArray.get(rightIndex) > leftArray.get(leftIndex)) {
        currentWeight += leftWeights.get(leftIndex);
        if ((double)currentWeight >= halfWay) {
          T = leftArray.get(leftIndex);
          break;
        }
        leftIndex += 1;
      } else {
        currentWeight += leftWeights.get(leftIndex) + rightWeights.get(rightIndex);
        if ((double)currentWeight >= halfWay) {
          T = rightArray.get(rightIndex);
          break;
        }
        leftIndex += 1;
        rightIndex += 1;
      }
    }
    System.out.println(T);
    //System.out.println("left: " + goingLeft + "  right: " + goingRight);
    int numMeetings = 0;
    for (int i = 0; i < goingLeft.size(); i++) {
      if (goingLeft.get(i)[2] == 1) {
        int minValue = 0;
        int maxValue = goingRight.size() - 1;
        int currentValue = -1;
        int oldValue = -2;
        while (currentValue != oldValue && minValue <= maxValue) {
          oldValue = currentValue;
          currentValue = (int)(minValue + maxValue)/2;
          //System.out.println(minValue + " " + maxValue + " = " + currentValue);
          if (goingRight.get(currentValue)[1] < goingLeft.get(i)[1] + T*2) {
            if (currentValue == goingRight.size() - 1 || (currentValue < goingRight.size() - 1 && goingRight.get(currentValue + 1)[1] > goingLeft.get(i)[1] + T*2)) {
              break;
            } 
            if (minValue == currentValue) {
              minValue += 1;
              currentValue = minValue;
            } else {
              minValue = currentValue;
            }
          } else {
            maxValue = currentValue;
          }
        }
        minValue = 0;
        maxValue = goingRight.size() - 1;
        int currentMinValue = -1;
        oldValue = -2;
        while (currentMinValue != oldValue && minValue <= maxValue) {
          oldValue = currentMinValue;
          currentMinValue = (int)(minValue + maxValue)/2;
          //System.out.println(minValue + " " + maxValue + " = " + currentMinValue);
          if (goingRight.get(currentMinValue)[1] < goingLeft.get(i)[1]) {
            if (minValue == currentMinValue) {
              minValue += 1;
              currentMinValue = minValue;
            } else {
              minValue = currentMinValue;
            }
          } else {
            maxValue = currentMinValue;
          }
        }
        //System.out.println(currentValue + " - " + currentMinValue);
        numMeetings += currentValue - currentMinValue + 1;
      }
    }


    /*
    int multiplier = 1;
    int index = 1;
    while (index < N) {
      System.out.println(currentIndex + " " + index);
      if (cowArray[currentIndex][1] + T >= cowArray[index][1] && cowArray[currentIndex][2] != cowArray[index][2]) {
        numMeetings += 1 * multiplier;
        index += 1;
      } else if (cowArray[currentIndex][1] + T >= cowArray[index][1] && cowArray[currentIndex][2] == cowArray[index][2]) {
        multiplier += 1;
        index += 1;
      } else {
        currentIndex += 1;
        while (currentIndex < cowArray.length && cowArray[currentIndex][2] != 1) {
          currentIndex += 1;
        }
        multiplier -= 1;
      }
    }*/
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
    pw.println(numMeetings);
    pw.close();
  }
}
/*
> _ > _ < _ <
_ > _ >< _ < _
_ _ >< _ >< _ _
_ < _ >< _ > _
< _ < _ > _ >
_ < _ _ _ > _ 
< _ _ _ _ _ >

> > < < <
*/

