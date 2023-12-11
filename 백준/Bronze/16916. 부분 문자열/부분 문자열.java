import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String S = br.readLine(), P = br.readLine();
            int[] pi = failureFunction(P);
            int n = S.length(), m = P.length(), j = 0;
            for (int i = 0; i < n; i++) {
                while (j > 0 && S.charAt(i) != P.charAt(j)) {
                    j = pi[j - 1];
                }
                if (S.charAt(i) == P.charAt(j)) {
                    if (j == m - 1) {
                        bw.write(String.valueOf(1));
                        bw.flush();
                        return;
                    } else j++;
                }
            }
            bw.write(String.valueOf(0));
            bw.flush();
        }
    }
    private static int[] failureFunction(String string) {
        int n = string.length(), j = 0;
        int[] array = new int[n];
        for (int i = 1; i < n; i++) {
            while (j > 0 && string.charAt(i) != string.charAt(j)) {
                j = array[j - 1];
            }
            if (string.charAt(i) == string.charAt(j)) {
                array[i] = ++j;
            }
        }
        return array;
    }
}