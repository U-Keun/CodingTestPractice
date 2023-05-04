import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = 1000000, max = -1000000;
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            if (input < min) min = input;
            if (input > max) max = input;
        }
        System.out.println(min + " " + max);
    }
}