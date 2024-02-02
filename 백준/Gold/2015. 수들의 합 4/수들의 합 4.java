import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        br.close();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        long result = 0;
        for (int i = 0; i < N; i++) {
            sum += Integer.parseInt(st.nextToken());
            result += map.getOrDefault(sum - K, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        System.out.println(result);
    }
}