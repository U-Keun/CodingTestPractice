import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int a, b, deg;
    static Map<Integer, List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        graph = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (int i = 0; i < 12; i++) {
            if (graph.get(i + 1).size() != 3) continue;
            deg = 0;
            for (int j:graph.get(i + 1)) {
                deg += graph.get(j).size();
            }
            if (deg == 6) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}