import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static List<Integer>[] graph;
    private static int[] record;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = readIntegers(br.readLine());
        int N = input[0], M = input[1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            input = readIntegers(br.readLine());
            graph[input[0]].add(input[1]);
        }
        br.close();

        record = new int[N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            if (findMatching(i)) {
                answer++;
            }

        }

        System.out.println(answer);
    }
    private static boolean findMatching(int student) {
        List<Integer> preference = graph[student];
        for (int laptop : preference) {
            if (visited[laptop]) continue;
            visited[laptop] = true;
            if (record[laptop] == 0 || findMatching(record[laptop])) {
                record[laptop] = student;
                return true;
            }
        }
        return false;
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}