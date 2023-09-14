import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()), answer = 1;
        for (int i = 0; i < N; i++) answer *= 2;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}