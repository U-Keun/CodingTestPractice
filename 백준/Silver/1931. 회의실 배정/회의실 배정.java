import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, a, b, count = 0, end = -1;
    static List<Integer[]> schedule = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            schedule.add(new Integer[]{a, b});
        }
        schedule.sort((s, t) -> {
            if (s[1].equals(t[1])) {
                return s[0] - t[0];
            } else {
                return s[1] - t[1];
            }
        });
        for (int i = 0; i < N; i++) {
            if (end <= schedule.get(i)[0]) {
                count++;
                end = schedule.get(i)[1];
            }
        }
        System.out.println(count);
    }
}