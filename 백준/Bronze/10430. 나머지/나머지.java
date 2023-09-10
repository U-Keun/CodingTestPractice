import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
        br.close();
        bw.write(((A + B) % C) + "\n" + ((A % C) + (B % C)) % C + "\n"
            + ((A * B) % C) + "\n" + ((A % C) * (B % C)) % C);
        bw.flush();
        bw.close();
    }
}