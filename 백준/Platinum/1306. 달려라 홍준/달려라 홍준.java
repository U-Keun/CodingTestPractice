import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] NM, ads;
    private static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        NM = readIntegers(br.readLine());
        ads = readIntegers(br.readLine());
        br.close();
        int height = (int) Math.ceil(Math.log(NM[0]) / Math.log(2));
        tree = new int[2 << height];
        generate(1, 0, NM[0] - 1);
        StringBuilder print = new StringBuilder();
        for (int i = NM[1] - 1; i < NM[0] - NM[1] + 1; i++) {
            print.append(getIntervalValue(1, 0, NM[0] - 1, i - NM[1] + 1, i + NM[1] - 1))
                    .append(" ");
        }
        System.out.println(print);
    }
    private static int generate(int current, int start, int end) {
        if (start == end) return tree[current] = ads[start];
        int mid = (start + end) >> 1;
        return tree[current] = Math.max(generate(current * 2, start, mid),
                generate(current * 2 + 1, mid + 1, end));
    }
    private static int getIntervalValue(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) >> 1;
        return Math.max(getIntervalValue(current * 2, start, mid, left, right),
                getIntervalValue(current * 2 + 1, mid + 1, end, left, right));
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}