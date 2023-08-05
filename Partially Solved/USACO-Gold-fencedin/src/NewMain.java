import java.io.*;
import java.util.*;

class NewMain {  
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("fencedin.in"));
        int A = s.nextInt();
        int B = s.nextInt();
        int n = s.nextInt(); //vertical fences
        int m = s.nextInt(); //horizontal fences

        int[] vFences = new int[n];
        int[] hFences = new int[m];
        for (int i = 0; i < n; i++) {
            vFences[i] = s.nextInt();
        }
        for (int i = 0; i < m; i++) {
            hFences[i] = s.nextInt();
        }
        Arrays.sort(vFences);
        int[] vLens = new int[n + 1];
        vLens[0] = vFences[0];
        vLens[n] = A - vFences[n - 1];
        for (int i = 1; i < n; i++) {
            vLens[i] = vFences[i] - vFences[i - 1];
        }
        Arrays.sort(vLens);

        Arrays.sort(hFences);
        int[] hLens = new int[m + 1];
        hLens[0] = hFences[0];
        hLens[m] = B - hFences[m - 1];
        for (int i = 1; i < m; i++) {
            hLens[i] = hFences[i] - hFences[i - 1];
        }
        Arrays.sort(hLens);

        long sum = 0;
        sum += vLens[0] * m;
        sum += hLens[0] * n;

        int vIndex = 1;
        int hIndex = 1;

        int vUsed = 0;
        int hUsed = 0;

        for (int i = 0; i < n + m; i++) {
            if (hIndex >= m + 1 || (vIndex < n + 1 && vLens[vIndex] < hLens[hIndex])) {
                sum += vLens[vIndex] * (m - hUsed);
                vUsed += 1;
                vIndex += 1;
            } else {
                sum += hLens[hIndex] * (n - vUsed);
                hUsed += 1;
                hIndex += 1;
            }
        }
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("fencedin.out"))));
        pw.println(sum);
        pw.close();
        
    }
}