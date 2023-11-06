import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st;
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int cow = Integer.parseInt(st.nextToken()), position = Integer.parseInt(st.nextToken());
                if (map.containsKey(cow)) {
                    if (map.get(cow) != position) {
                        count++;
                        map.put(cow, position);
                    }
                } else {
                    map.put(cow, position);
                }
            }
            bw.write(String.valueOf(count));
            bw.flush();
        }
    }
}