import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        long a = Integer.parseInt(input[0]), b = Integer.parseInt(input[1]);
        input = br.readLine().split(" ");
        long c = Integer.parseInt(input[0]), d = Integer.parseInt(input[1]);
        br.close();
        long den = b * d / gcd(b, d), num = a * den / b + c * den / d;
        bw.write(num / gcd(den, num) + " " + den / gcd(den, num));
        bw.flush();
        bw.close();
    }
    public static long gcd(long x, long y) {
        long a = Math.min(x, y), b = Math.max(x, y);
        while (b % a != 0) {
            long tmp = b % a;
            b = a;
            a = tmp;
        }
        return a;
    }
}