import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new int[4 * N];
        generateTree(1, 0, N - 1);
        int[] inversionSeq = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        int[] permutation = new int[N];
        for (int i = N; i > 0; i--) {
            permutation[insertNumber(1, 0, N - 1, inversionSeq[i - 1])] = i;
        }
        String result = Arrays.stream(permutation)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }
    private static int generateTree(int current, int start, int end) {
        if (start == end) {
            return tree[current] = 1;
        }
        int mid = (start + end) >> 1;
        return tree[current] = generateTree(current * 2, start, mid)
                + generateTree(current * 2 + 1, mid + 1, end);
    }
    private static int insertNumber(int current, int start, int end, int count) {
        tree[current]--;
        if (start == end) return start;
        int mid = (start + end) >> 1;
        if (tree[current * 2 + 1] > count) {
            return insertNumber(current * 2 + 1, mid + 1, end, count);
        }
        return insertNumber(current * 2, start, mid, count - tree[current * 2 + 1]);
    }

}