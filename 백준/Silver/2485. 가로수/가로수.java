import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()), gcd = 0;
        int[] numbers = new int[N];
        numbers[0] = Integer.parseInt(br.readLine());
        for (int i = 1; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            int y = numbers[i] - numbers[i - 1];
            if (gcd == 0) gcd = y;
            else gcd = gcd(gcd, y);
        }
        br.close();
        bw.write(String.valueOf((numbers[N - 1] - numbers[0]) / gcd - N + 1));
        bw.flush();
        bw.close();
    }
    public static int gcd(int x, int y) {
        int a = Math.min(x, y), b = Math.max(x, y);
        while (b % a != 0) {
            int tmp = b % a;
            b = a;
            a = tmp;
        }
        return a;
    }
}