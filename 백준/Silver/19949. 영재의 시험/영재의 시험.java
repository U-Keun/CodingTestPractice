import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] solution;
    private static int caseCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        backtracking(0, 0, 0, 0);
        System.out.println(caseCount);
    }
    private static void backtracking(int depth, int score, int prevSelection, int stack) {
        if (depth == 10) {
            if (score >= 5) caseCount++;
            return;
        }
        for (int i = 1; i <= 5; i++) {
            if (i == prevSelection) {
                if (stack == 2) {
                    continue;
                }
                backtracking(depth + 1,
                        i == solution[depth] ? score + 1 : score,
                        i,
                        stack + 1);
            } else {
                backtracking(depth + 1,
                        i == solution[depth] ? score + 1 : score,
                        i,
                        1);
            }
        }
    }
}