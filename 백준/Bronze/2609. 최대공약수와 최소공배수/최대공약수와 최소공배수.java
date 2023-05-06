import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int tmp;
        int a = Math.max(N, M);
        int b = Math.min(N, M);
        while ( a % b != 0 ) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        System.out.println(b);
        System.out.println(N * M / b);
    }
}