import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer numbers = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(numbers.nextToken());
        int C = Integer.parseInt(numbers.nextToken());
        int W = Integer.parseInt(numbers.nextToken());
        int answer = 0;
        int[][] array = new int[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (j == 0 || (i == j)) {
                    array[i][j] = 1;
                } else if (i < j) {
                    array[i][j] = 0;
                } else {
                    array[i][j] = array[i-1][j-1] + array[i-1][j];
                }
            }
        }
        for (int i = 0; i < W; i++) {
            for (int j = C - 1; j < C + i; j++) {
                answer += array[R + i - 1][j];
            }
        }
        System.out.println(answer);
    }

}