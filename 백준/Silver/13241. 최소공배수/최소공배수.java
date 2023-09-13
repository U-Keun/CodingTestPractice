import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        br.close();
        long A = Long.parseLong(input[0]), B = Long.parseLong(input[1]);
        bw.write(String.valueOf(A * B / gcd(A, B)));
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