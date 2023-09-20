import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken()), B = Long.parseLong(st.nextToken());
            bw.write(String.valueOf(countOnes(B) - countOnes(A - 1)));
            bw.flush();
        }
    }
    public static long countOnes(long i) {
        if (i <= 0) return 0;
        if (i == 1) return 1;
        int k = Long.toBinaryString(i).length() - 1;
        long power = pow(2, k - 1), answer = k * power;
        answer += i - power * 2 + 1 + countOnes(i - power * 2);
        return answer;
    }
    public static long pow(int base, int exponent) {
        if (exponent == 0) return 1;
        else if (exponent % 2 == 0) {
            long halfPower = pow(base, exponent / 2);
            return halfPower * halfPower;
        } else {
            long halfPower = pow(base, (exponent - 1) / 2);
            return base * halfPower * halfPower;
        }
    }
}