import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        StringTokenizer memoryInput = new StringTokenizer(br.readLine()),
                resourceInput = new StringTokenizer(br.readLine());
        br.close();
        int[] memories = new int[N], resources = new int[N];
        int totalMemory = 0;
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(memoryInput.nextToken());
            resources[i] = Integer.parseInt(resourceInput.nextToken());
            totalMemory += memories[i];
        }

        int[] dp = new int[totalMemory + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = totalMemory; j >= memories[i]; j--) {
                dp[j] = Math.min(dp[j], dp[j - memories[i]] + resources[i]);
            }
        }

        int answer = 10001;
        for (int i = M; i <= totalMemory; i++) {
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
    }
}