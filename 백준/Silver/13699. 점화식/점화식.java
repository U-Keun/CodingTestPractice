import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static long[] catalan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        catalan = new long[n];
        if (n != 0) catalan[0] = 1;
        System.out.println(function(n));
    }
    public static long function(int n) {
        long answer = 0;
        if (n == 0) {
            return 1;
        } else {
            if (catalan[n-1] != 0) {
                return catalan[n-1];
            } else {
                for (int i = 0; i < n; i++) {
                    answer += function(i) * function(n - i - 1);
                }
                catalan[n-1] = answer;
                return answer;
            }
        }
    }
}