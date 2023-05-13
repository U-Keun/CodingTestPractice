import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] times = new int[N];
        for (int i=0; i<N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times);
        int answer = 0;
        for (int i=0; i<N; i++) {
            answer += (N-i) * times[i];
        }
        System.out.println(answer);
    }
}