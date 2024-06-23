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
        
        if (N == 1) {
            System.out.println(1);
            return;
        }
        
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int kinds = 0, p1 = 0, p2 = 1;
        int[] counts = new int[10];
        counts[array[p1]]++;
        counts[array[p2]]++;
        if (counts[array[p1]] == 1) kinds += 2;
        else kinds++;

        int answer = 0;
        while (p2 < N) {
            while (p2 < N && kinds <= 2) {
                p2++;
                if (p2 < N && counts[array[p2]]++ == 0) kinds++;
            }

            answer = Math.max(answer, p2 - p1);

            while (kinds > 2) {
                if (--counts[array[p1++]] == 0) kinds--;
            }
        }
        System.out.println(answer);
    }
}