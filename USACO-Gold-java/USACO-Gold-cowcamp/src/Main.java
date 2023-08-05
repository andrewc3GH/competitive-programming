import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("cowcamp.in"));
        //Scanner s = new Scanner(System.in);
        int T = s.nextInt(); //num cases
        int K = s.nextInt(); //num submissions
        
        double chance = (double)1/T; //dont change
        double otherChance = (double)(T-1)/T; //dont change
        double first = chance;
        for (int i = 0; i < (K - 1); i++) {
            first = otherChance * first;
            first += chance;
        }
        double split = (1 - first) / (T - 1);
        System.out.println(first + " " + split);

        double ans = first * T;
        for (int i = 1; i < T; i++) {
            ans += i * split;
        }
        System.out.println(ans);
        
    }
}