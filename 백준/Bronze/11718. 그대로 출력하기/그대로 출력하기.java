import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String input = "";
        while ((input = br.readLine()) != null) {
            bw.write(input + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}