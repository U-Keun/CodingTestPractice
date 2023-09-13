import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "1":
                    int k = Integer.parseInt(st.nextToken());
                    stack.add(0, k);
                    break;
                case "2":
                    if (stack.size() > 0) {
                        bw.write(stack.get(0) + "\n");
                        stack.remove(0);
                    } else bw.write(-1 + "\n");
                    break;
                case "3":
                    bw.write(stack.size() + "\n");
                    break;
                case "4":
                    if (stack.size() > 0) bw.write(0 + "\n");
                    else bw.write(1 + "\n");
                    break;
                case "5":
                    if (stack.size() > 0) bw.write(stack.get(0) + "\n");
                    else bw.write(-1 + "\n");
                    break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}