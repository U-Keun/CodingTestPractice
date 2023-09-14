import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            BigInteger den = BigInteger.ONE, answer = BigInteger.ONE;
            for (int j = 0; j < a; j++) {
                answer = answer.multiply(BigInteger.valueOf(b - j));
                den = den.multiply(BigInteger.valueOf(a - j));
            }
            answer = answer.divide(den);
            print.append(answer).append('\n');
        }
        br.close();
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
}