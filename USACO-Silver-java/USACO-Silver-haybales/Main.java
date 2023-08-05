import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("haybales.in"));
    int N = s.nextInt();
    int Q = s.nextInt();
    int[] intArray = new int[N]; 
    for(int i = 0; i < N; i++) {
      intArray[i] = s.nextInt();
    }
    Arrays.sort(intArray, 0, intArray.length);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
    for(int i = 0; i < Q; i++) {
      int bottom = s.nextInt();
      int top = s.nextInt();
      int oldNum = -1;
      if (bottom > intArray[intArray.length - 1] || top < intArray[0]) {
        pw.println(0);
        continue;
      }
      int bottomRange = 0;
      int topRange = intArray.length - 1;
      int counter = (topRange + bottomRange)/2;
      while (oldNum != counter) {
        oldNum = counter;
        if (bottom > intArray[counter]) {
          bottomRange = counter;
        } else if (bottom == counter){
          break;
        } 
        if (bottom < intArray[counter]){
          topRange = counter;
        }
        counter = (topRange + bottomRange)/2;
      }
      int bottomIndex = counter;
      bottomRange = 0;
      topRange = intArray.length - 1;
      counter = (topRange + bottomRange)/2;
      oldNum = -1;
      while (oldNum != counter) {
        oldNum = counter;
        if (top < intArray[counter]) {
          topRange = counter;
        } else if (top == counter){
          break;
        } 
        if (top > intArray[counter]){
          bottomRange = counter + 1;
        }
        counter = (topRange + bottomRange)/2;
      }
      int topIndex = counter;
      int subtract = 0;
      for (int j = bottomIndex; j < topIndex; j++) {
        if (intArray[j] < bottom) {
          subtract += 1;
        } else {
          break;
        }
      }
      for (int k = topIndex; k > 0; k -= 1) {
        if (intArray[k] > top) {
          subtract += 1;
        } else {
          break;
        }
      }
      int answer = topIndex - bottomIndex + 1 - subtract;
      pw.println(answer);
    }
    pw.close();
  }
}