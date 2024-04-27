import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = 1000 - Integer.parseInt(br.readLine());

        int coins = 0;
        coins += money / 500;
        money %= 500;
        coins += money /100;
        money %= 100;
        coins += money / 50;
        money %= 50;
        coins += money / 10;
        money %= 10;
        coins += money / 5;
        money %= 5;
        coins += money;
        System.out.println(coins);
    }
}