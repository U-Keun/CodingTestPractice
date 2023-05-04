import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int k;
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            k = Integer.parseInt(st2.nextToken());
            if (k < X) answer.add(k);
        }
        for (int j:answer) {
            System.out.print(j + " ");
        }
    }
}