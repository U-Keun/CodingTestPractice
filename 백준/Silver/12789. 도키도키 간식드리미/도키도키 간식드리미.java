import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()), order = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        Queue<Integer> line1 = new LinkedList<>();
        Stack<Integer> line2 = new Stack<>();
        for (int i = 0; i < N; i++) line1.add(Integer.parseInt(st.nextToken()));
        while (!line1.isEmpty()) {
            int k = line1.poll();
            if (order == k) {
                order++;
                while (!line2.isEmpty() && line2.peek() == order) {
                    line2.pop();
                    order++;
                }
            } else line2.push(k);
        }
        if (line2.isEmpty()) bw.write("Nice");
        else bw.write("Sad");
        bw.flush();
        bw.close();
    }
}