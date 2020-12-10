/*
ID: cruzan1
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.*;

class pprime {
  public static ArrayList<Integer> palindromeLst;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("pprime.in"));
    int a = s.nextInt();
    int b = s.nextInt();
    palindromeLst = new ArrayList<>();
    generatePalindrome();
    ArrayList<Integer> ans = new ArrayList<>();
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
    for (int num : palindromeLst) {
      if (checkPrime(num) && a <= num && b >= num) {
        ans.add(num);
      }
    }
    Collections.sort(ans);
    for (int answer : ans) {
      pw.println(answer);
    }
    pw.close();
  }

  public static boolean checkPrime(int number) {
    for (int i = 2; i <= Math.sqrt(number); i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void generatePalindrome() {
    for (int a = 1; a < 10; a++) {
      palindromeLst.add(Integer.parseInt("" + a));
      palindromeLst.add(Integer.parseInt("" + a + a));
      for (int b = 0; b < 10; b++) {
        palindromeLst.add(Integer.parseInt("" + a + b + b + a));
        palindromeLst.add(Integer.parseInt("" + a + b + a));
        for (int c = 0; c < 10; c++) {
          palindromeLst.add(Integer.parseInt("" + a + b + c + c + b + a));
          palindromeLst.add(Integer.parseInt("" + a + b + c + b + a));
          for (int d = 0; d < 10; d++) {
            palindromeLst.add(Integer.parseInt("" + a + b + c + d + d + c + b + a));
            palindromeLst.add(Integer.parseInt("" + a + b + c + d + c + b + a));
          }
        }
      }
    }
  } 

}