import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());

        Set<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st2.nextToken()));
        }
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B.add(Integer.parseInt(st3.nextToken()));
        }
        Set<Integer> U = new HashSet<>(A);
        U.addAll(B);
        Set<Integer> I = new HashSet<>(A);
        I.retainAll(B);
        int cnt = 0;
        for (Integer i:U){
            if (!I.contains(i)) cnt++;
        }
        System.out.println(cnt);
    }
}