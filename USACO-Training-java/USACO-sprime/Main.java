/*
ID: cruzan1
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.*;

class sprime {
  public static ArrayList<Integer> superPrimeLst;
  public static int N;
  public static int counter;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("sprime.in"));
    N = s.nextInt();
    superPrimeLst = new ArrayList<Integer>();
    counter = 0;
    findSuperPrimes(2);
    findSuperPrimes(3);
    findSuperPrimes(5);
    findSuperPrimes(7);
    Collections.sort(superPrimeLst);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
    for (int i : superPrimeLst) {
      pw.println(i);
    }
    pw.close();
    System.out.println(counter);
  }

  public static void findSuperPrimes(int a) {
    counter += 1;
    if (isPrime(a)) {
      String stringA = Integer.toString(a);
      if (stringA.length() == N) {
        superPrimeLst.add(a);
      } else {
        findSuperPrimes(Integer.parseInt(stringA + "1"));
        findSuperPrimes(Integer.parseInt(stringA + "3"));
        findSuperPrimes(Integer.parseInt(stringA + "7"));
        findSuperPrimes(Integer.parseInt(stringA + "9"));
      }
    }
  } 

  public static boolean isPrime(int a) {
    if (a == 1) {
      return false;
    } else if (a == 2) {
      return true;
    } else if (a % 2 == 0) {
      return false;
    }
    for (int i = 3; i < Math.sqrt(a); i += 2) {
      counter += 1;
      if (a % i == 0) {
        return false;
      }
    }
    return true;
  }
}