import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int input = Integer.parseInt(br.readLine()) - 543;
        br.close();
        bw.write(input + "\n");
        bw.flush();
        bw.close();
    }
}