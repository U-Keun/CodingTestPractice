import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int N, M;
        Set<String> memo = new HashSet<>();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                memo.add(st1.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (memo.contains(st2.nextToken())) print.append(1 + "\n");
                else print.append(0 + "\n");
            }
            memo.clear();
        }
        System.out.println(print);
    }
}