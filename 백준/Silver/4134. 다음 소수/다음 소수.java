import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            long number = Long.parseLong(br.readLine());
            if (number < 2) {
                bw.write(2 + "\n");
                continue;
            }
            while (true) {
                boolean isPrime = true;
                for (long j = 2; j * j <= number; j++) {
                    if (number % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) break;
                number++;
            }
            bw.write(number + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}