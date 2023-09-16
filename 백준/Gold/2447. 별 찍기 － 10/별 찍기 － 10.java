import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] star = {"*"};

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        br.close();
        StringBuilder print = new StringBuilder();
        String[] answer = recursion(N);
        for (String s:answer) print.append(s).append('\n');
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
    public static String[] recursion(int k) {
        if (k == 1) return star;
        String[] answer = new String[k];
        String[] part = recursion(k / 3);
        for (int i = 0; i < k; i++) {
            StringBuilder sb = new StringBuilder();
            if (i < k / 3 || i >= 2 * k / 3) {
                sb.append(part[i % (k / 3)].repeat(3));
            } else {
                sb.append(part[i % (k / 3)]).append(" ".repeat(k / 3)).append(part[i % (k / 3)]);
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}