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
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, String> poketmon = new HashMap<>();
        for(int i=1; i<=N; i++){
            String name = br.readLine();
            String num = Integer.toString(i);
            poketmon.put(name, num);
            poketmon.put(num, name);
        }

        for(int i=0; i<M; i++){
            sb.append(poketmon.get(br.readLine())).append("\n");
        }
        System.out.println(sb);
    }
}