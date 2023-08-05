import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Random rand = new Random(); 

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("testcase.out")));

        int N = 100;
        pw.println(N);
        for (int i = 0; i < N; i++) {
            int rand_int1 = rand.nextInt(200) + 1; 
            int rand_int2 = rand.nextInt(200) + 1 + rand_int1; 
            pw.println(rand_int1 + " " + rand_int2);
        }
        pw.close();
    }
}
