import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, k;
    static String input;
    static Queue<Integer> site;
    static int answer;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        input = br.readLine();
        site = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == 'E') {
                site.add(i);
            }
        }
        answer = 0;
        while (!site.isEmpty()) {
            k = site.poll();
            for (int i = k + 1; i < N ; i++) {
                if (input.charAt(i) == 'E') site.poll();
                else break;
            }
            answer++;
        }
        System.out.println(answer);
    }
}