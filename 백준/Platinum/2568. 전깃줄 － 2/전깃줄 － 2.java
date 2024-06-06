import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Edge> edges = new ArrayList<>();
        Map<Integer, Integer> reversedIndex = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            reversedIndex.put(B, A);
            edges.add(new Edge(A, B));
        }
        br.close();
        edges.sort(Comparator.comparingInt(a -> a.index));

        List<Integer> lis = new ArrayList<>();
        int[] record = new int[N];

        for (int i = 0; i < N; i++) {
            int target = edges.get(i).number;
            int index = binarySearch(lis, target);
            record[i] = index;
            if (lis.size() == index) {
                lis.add(target);
            } else {
                lis.set(index, target);
            }
        }

        StringBuilder print = new StringBuilder();
        print.append(N - lis.size()).append("\n");

        int l = lis.size() - 1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            if (record[i] == l) {
                l--;
                continue;
            }
            stack.addFirst(reversedIndex.get(edges.get(i).number));
        }
        while (!stack.isEmpty()) {
            print.append(stack.pollFirst()).append("\n");
        }

        System.out.println(print);
    }

    static class Edge {
        public int index, number;
        Edge(int index, int number) {
            this.index = index;
            this.number = number;
        }
    }

    private static int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}