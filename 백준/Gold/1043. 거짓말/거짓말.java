import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, tmp, answer;
    static Queue<Integer> knewTruth;
    static boolean[] visited;
    static Set<Integer> knowTruth = new HashSet<>();
    static Map<Integer, Set<Integer>> graph = new HashMap<>();
    static Integer[] party;
    static List<Set<Integer>> parties;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tmp = Integer.parseInt(st.nextToken());
        knewTruth = new LinkedList<>();
        for (int i = 0; i < tmp; i++) {
            knewTruth.add(Integer.parseInt(st.nextToken()));
        }
        parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            tmp = Integer.parseInt(st.nextToken());
            party = new Integer[tmp];
            for (int j = 0; j < tmp; j++) {
                party[j] = Integer.parseInt(st.nextToken());
            }
            parties.add(new HashSet<>(Arrays.asList(party)));
            for (int j = 0; j < tmp; j++) {
                for (int k = j + 1; k < tmp; k++) {
                    int v1 = party[j];
                    int v2 = party[k];
                    graph.putIfAbsent(v1, new HashSet<>());
                    graph.putIfAbsent(v2, new HashSet<>());
                    graph.get(v1).add(v2);
                    graph.get(v2).add(v1);
                }
            }
        }
        visited = new boolean[N];
        while (!knewTruth.isEmpty()) {
            tmp = knewTruth.poll();
            if (visited[tmp - 1]) continue;
            visited[tmp - 1] = true;
            knowTruth.add(tmp);
            knewTruth.addAll(graph.getOrDefault(tmp, new HashSet<>(){}));
            knowTruth.addAll(graph.getOrDefault(tmp, new HashSet<>(){}));

        }
        answer = 0;
        for (Set<Integer> s:parties) {
            if (intersection(s, knowTruth) == 0) answer++;
        }
        System.out.println(answer);
    }
    static int intersection(Set<Integer> A, Set<Integer> B) {
        int a = A.size();
        int b = B.size();
        Set<Integer> C = new HashSet<>(A);
        C.addAll(B);
        return a + b - C.size();
    }
}