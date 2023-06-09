import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static char[] input;
    static int[] order;
    static int N;

    public static void main(String[] args) throws IOException {
        input = br.readLine().toCharArray();
        N = input.length;
        order = new String(input).chars().map(c -> c - 'A').toArray();
        while (getWord().length() < N) {
            recursion(input);
        }
        System.out.println(print);
    }
    static void recursion(char[] input) {
        int l = input.length;
        if (l == 0) {
            return;
        }
        int count = 0;
        for (int i = 0; i < l; i++) {
            if (order[N - l + i] != 26) count++;
        }
        int min, idx;
        for (int i = 0; i < count; i++) {

            min = 26;
            idx = -1;
            for (int j = 0; j < l; j++) {
                if (min > order[N - l + j]) {
                    min = order[N - l + j];
                    idx = j;
                }
            }
            if (idx == -1) return;
            order[idx + N - l] = 26;
            print.append(getWord()).append('\n');
            recursion(Arrays.copyOfRange(input, idx + 1, l));
        }
    }
    static String getWord() {
        int l = input.length;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < l; i++) {
            if (order[i] != 26) continue;
            answer.append(input[i]);
        }
        return answer.toString();
    }
}