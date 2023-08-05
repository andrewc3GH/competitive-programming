import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("applecatching.in"));
        //Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        ArrayList<int[]> cowArray = new ArrayList<>();
        ArrayList<int[]> appleArray = new ArrayList<>();
        //q, time, xLocation, and number
        for (int i = 0; i < N; i++) {
            if (s.nextInt() == 1) {
                cowArray.add(new int[]{s.nextInt(), s.nextInt(), s.nextInt()});
            } else {
                appleArray.add(new int[]{s.nextInt(), s.nextInt(), s.nextInt()});
            }
        }
        Collections.sort(cowArray, (int[] a, int[] b) -> {
            return a[1] - b[1];
        });
        Collections.sort(appleArray, (int[] a, int[] b) -> {
            return a[1] - b[1];
        });
        int ans = 0;
        int appleIndex = 0;
        System.out.println("here");
        for (int i = 0; i < cowArray.size(); i++) {
            while (appleIndex < appleArray.size()) {
                System.out.println(appleIndex + " " + appleArray.size());
                if (appleArray.get(appleIndex)[1] < cowArray.get(i)[1]) {
                    if (Math.abs(appleArray.get(appleIndex)[2] - cowArray.get(i)[2]) <= appleArray.get(appleIndex)[1] - cowArray.get(i)[1]) {
                        if (appleArray.get(appleIndex)[3] > cowArray.get(i)[3]) { //more apples than cows
                            ans += cowArray.get(i)[3];
                            appleArray.get(appleIndex)[3] -= cowArray.get(i)[3];
                            break;
                        } else {
                            ans += appleArray.get(appleIndex)[3];
                            cowArray.get(i)[3] -= appleArray.get(appleIndex)[3];
                            appleIndex += 1;
                        }
                    } else {
                        appleIndex += 1;
                    }
                } else {
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}
