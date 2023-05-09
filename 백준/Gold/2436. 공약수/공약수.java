import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int gcd = Integer.parseInt(st.nextToken());
        int lcm = Integer.parseInt(st.nextToken());
        int xDvidedByGCD = 0, yDvidedByGCD = 0;
        int range = (int) Math.round(Math.sqrt((double) lcm / gcd));
        int minSum = lcm / gcd + 1;
        int x = 0, y = 0;
        for (int i = range; i > 0; i--) {
            xDvidedByGCD = i;
            if (lcm / gcd % i != 0) continue;
            yDvidedByGCD = lcm / gcd / i;
            if (getGCD(xDvidedByGCD, yDvidedByGCD) == 1 && xDvidedByGCD + yDvidedByGCD <= minSum) {
                minSum = xDvidedByGCD + yDvidedByGCD;
                x = xDvidedByGCD * gcd;
                y = yDvidedByGCD * gcd;
            }
        }
        System.out.println((x) + " " + (y));
    }
    static int getGCD(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int tmp;
        while (max % min != 0) {
            tmp = max % min;
            max = min;
            min = tmp;
        }
        return min;
    }
}