import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long answer = 1;
        for (int i = 0; i < N; i++) answer *= i + 1;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}