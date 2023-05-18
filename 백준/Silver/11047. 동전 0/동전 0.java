import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int money = Integer.parseInt(input[1]);
        Integer[] coins = new Integer[N];
        for (int i=0; i<N; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (money < coin) coins[i] = 0;
            else coins[i] = coin;
        }
        int answer = 0;
        Arrays.sort(coins,Collections.reverseOrder());
        for (int c : coins) {
            if (money == 0 || c == 0) break;
            else {
                answer += money / c;
                money = money % c;
            }
        }
        System.out.println(answer);
    }
}