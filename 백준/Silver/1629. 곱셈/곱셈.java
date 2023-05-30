import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long A, B, C;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        System.out.println(modPow(A, B, C));
    }
    static long modPow(long A, long B, long C) {
        long res = 1;
        A = A % C;
        while (B > 0) {
            if (B % 2 == 1) {
                res = (res * A) % C;
            }
            A = (A * A) % C;
            B /= 2;
        }
        return res % C;
    }
}