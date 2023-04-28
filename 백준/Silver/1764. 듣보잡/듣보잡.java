import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int count = 0;
        TreeSet<String> names = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            names.add(br.readLine());
        }
        String newName;
        TreeSet<String> answer = new TreeSet<>();
        for (int j = 0; j < M; j++) {
            newName = br.readLine();
            if (names.contains(newName)) {
                count++;
                answer.add(newName);
            }
        }
        for (String s:answer) {
            print.append(s + '\n');
        }
        System.out.printf("%d%n" + print, count);
    }
}