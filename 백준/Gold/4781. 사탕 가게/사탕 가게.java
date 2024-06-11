import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] dp;
        StringBuilder print = new StringBuilder();
        while (!input.equals("0 0.00")) {
            String[] split = input.split(" ");
            int n = Integer.parseInt(split[0]),
                k = (int) Math.round(Float.parseFloat(split[1]) * 100.0);
            dp = new int[k + 1];
            for (int i = 0; i < n; i++) {
                String[] candy = br.readLine().split(" ");
                int calory = Integer.parseInt(candy[0]),
                    price = (int) Math.round(Float.parseFloat(candy[1]) * 100.0);
                for (int j = price; j <= k; j++) {
                    dp[j] = Math.max(dp[j], dp[j - price] + calory);
                }
            }
            print.append(dp[k]).append("\n");
            input = br.readLine();
        }
        br.close();
        System.out.println(print);
    }
}