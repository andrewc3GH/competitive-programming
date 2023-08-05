
import java.util.*;

class Main {  
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        String a = s.next();
        String b = s.next();
        String[] aArr = new String[N];
        String[] bArr = new String[N];
        for (int i = 0; i < N; i++) {
            aArr[i] = Character.toString(a.charAt(i));
            bArr[i] = Character.toString(b.charAt(i));
        }
        System.out.println(Arrays.toString(aArr));
        System.out.println(Arrays.toString(bArr));

        int[][] arr = new int[N + 1][N + 1];
        
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = 0;
                } else {
                    int add = 0;
                    if (aArr[i - 1].equals(bArr[j - 1])) {
                        add = 1;
                    }
                    int otherVar = Math.max(arr[i - 1][j], arr[i][j - 1]);
                    arr[i][j] = Math.max(otherVar, (arr[i - 1][j - 1] + add));
                }
            }
        }
        for (int[] array : arr) {
            System.out.println(Arrays.toString(array));
        }
    }
}
