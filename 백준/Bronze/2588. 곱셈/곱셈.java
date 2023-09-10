import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int A = Integer.parseInt(br.readLine()), B = Integer.parseInt(br.readLine());
        br.close();
        bw.write(A * (B % 10) + "\n" + A * (B / 10 % 10) + "\n" + A * (B / 100) + "\n" +
                A * B);
        bw.flush();
        bw.close();
    }
}