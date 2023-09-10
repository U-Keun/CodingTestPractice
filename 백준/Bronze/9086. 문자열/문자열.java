import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String input = br.readLine();
            String output = "" + input.charAt(0) + input.charAt(input.length() - 1);
            bw.write(output + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}