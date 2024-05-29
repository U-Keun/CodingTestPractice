import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        boolean[] visited = new boolean[2001];
        int number;
        for (int i = 0; i < N; i++) {
            number = Integer.parseInt(st.nextToken());
            visited[1000 + number] = true;
        }
        StringBuilder print = new StringBuilder();
        for (int i = -1000; i < 1001; i++) {
            if (!visited[1000 + i]) continue;
            print.append(i).append(" ");
        }
        System.out.println(print);
    }
}