import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long A = Long.parseLong(st.nextToken()), B = Long.parseLong(st.nextToken());
        long gcd = gcd(A, B);
        StringBuilder answer = new StringBuilder();
        while (gcd-- > 0) {
            answer.append("1");
        }
        System.out.println(answer);
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}