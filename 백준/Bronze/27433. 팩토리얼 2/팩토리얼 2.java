import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        br.close();
        bw.write(String.valueOf(factorial(N)));
        bw.flush();
        bw.close();
    }
    public static long factorial(int n) {
        if (n == 0) return 1;
        return factorial(n - 1) * n;
    }
}