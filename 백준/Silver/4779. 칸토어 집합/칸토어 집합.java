import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String input;
        StringBuilder print = new StringBuilder();
        while ((input = br.readLine()) != null) {
            int k = Integer.parseInt(input);
            print.append(recursion(k)).append('\n');
        }
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
    public static String recursion(int k) {
        if (k == 0) return "-";
        StringBuilder sb = new StringBuilder();
        String part = recursion(k - 1);
        sb.append(part).append(" ".repeat((int) Math.pow(3, k - 1))).append(part);
        return sb.toString();
    }
}