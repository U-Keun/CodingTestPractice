import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken()), B = Long.parseLong(st.nextToken()), C = Long.parseLong(st.nextToken());
        long answer = A + B + C;
        br.close();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}