import java.io.*;
import java.util.*;

public class Main {
    static char[] orders = {'D','S', 'L', 'R'};
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
                Queue<Integer> forward = new LinkedList<>();
                Queue<Integer> backward = new LinkedList<>();
                String[] visited = new String[10000];
                visited[A] = "";
                forward.add(A);
                Loop: while (true) {
                    Integer a = forward.poll();
                    for (char c:orders) {
                        Integer converted = convert(a, c);
                        if (converted == B) {
                            print.append(visited[a]).append(c);
                            break Loop;
                        }
                        if (visited[converted] != null) continue;
                        visited[converted] = visited[a] + c;
                        forward.add(converted);
                    }
                }
                print.append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
    public static Integer convert(Integer s, char c) {
        switch (c) {
            case 'D': s = (s * 2 > 9999) ? s * 2 % 10000 : s * 2; break;
            case 'S': s = (s == 0) ? 9999 : s - 1; break;
            case 'L': s = s / 1000 + s % 1000 * 10; break;
            case 'R': s = s % 10 * 1000 + s / 10; break;
        }
        return s;
    }
}