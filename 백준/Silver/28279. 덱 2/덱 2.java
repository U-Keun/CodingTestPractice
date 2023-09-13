import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "1": deque.add(0, Integer.parseInt(st.nextToken())); break;
                case "2": deque.add(Integer.parseInt(st.nextToken())); break;
                case "3":
                    if ((deque.size() > 0)) {
                        bw.write(deque.get(0) + "\n");
                        deque.remove(0);
                    } else bw.write(-1 + "\n"); break;
                case "4":
                    if (deque.size() > 0) {
                        bw.write(deque.get(deque.size() - 1) + "\n");
                        deque.remove(deque.size() - 1);
                    } else bw.write(-1 + "\n"); break;
                case "5": bw.write(deque.size() + "\n"); break;
                case "6":
                    if (deque.size() > 0) bw.write(0 + "\n");
                    else bw.write(1 + "\n"); break;
                case "7":
                    if (deque.size() > 0) bw.write(deque.get(0) + "\n");
                    else bw.write(-1 + "\n"); break;
                case "8":
                    if (deque.size() > 0) bw.write(deque.get(deque.size() - 1) + "\n");
                    else bw.write(-1 + "\n"); break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}