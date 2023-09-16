import java.io.*;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        br.close();
        ArrayList<Integer[]> answer = recursion(N, 1, 3);
        StringBuilder print = new StringBuilder();
        print.append(answer.size()).append('\n');
        for (Integer[] step:answer) {
            print.append(step[0]).append(" ").append(step[1]).append('\n');
        }
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
    public static ArrayList<Integer[]> recursion(int N, int a, int b) {
        ArrayList<Integer[]> answer;
        if (N == 1) {
            answer = new ArrayList<>();
            answer.add(new Integer[]{a, b});
            return answer;
        }
        int c = 6 - a - b;
        answer = recursion(N - 1, a, c);
        answer.add(new Integer[]{a, b});
        answer.addAll(recursion(N - 1, c, b));
        return answer;
    }
}