import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] input1, input2;
    static int[][] memo;
    static StringBuilder print = new StringBuilder();
    static int l1, l2;

    public static void main(String[] args) throws IOException {
        setVariables();
        for (int i = 1; i <= l2; i++) {
            for (int j = 1; j <= l1; j++) {
                if (input1[j - 1] == input2[i - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        System.out.println(memo[l2][l1]);
        while (l1 > 0 && l2 > 0) {
            if (memo[l2][l1] == memo[l2 - 1][l1]) l2--;
            else if (memo[l2][l1] == memo[l2][l1 - 1]) l1--;
            else {
                print.append(input2[l2 - 1]);
                l1--;
                l2--;
            }
        }
        System.out.println(print.reverse().toString());
    }
    static void setVariables() throws IOException {
        input1 = br.readLine().toCharArray();
        input2 = br.readLine().toCharArray();
        l1 = input1.length;
        l2 = input2.length;
        memo = new int[l2 + 1][l1 + 1];
    }
}