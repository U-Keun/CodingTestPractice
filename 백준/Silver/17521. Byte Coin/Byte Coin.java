import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        Long money = Long.parseLong(input[1]);
        int[] predictions = new int[n];
        for (int i = 0; i < n; i++) {
            predictions[i] = Integer.parseInt(br.readLine());
        }
        long coins = 0L;
        int diff;
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                diff = predictions[i + 1] - predictions[i];
                if (diff > 0 && coins == 0) {
                    coins = money / predictions[i];
                    money %= predictions[i];
                } else if (diff > 0 && coins != 0) continue;
                else if (diff < 0 && coins != 0) {
                    money += (long) predictions[i] * coins;
                    coins = 0L;
                } else continue;
            } else {
                if (coins != 0) money += (long) predictions[i] * coins;
            }
        }
        System.out.println(money);
    }
}