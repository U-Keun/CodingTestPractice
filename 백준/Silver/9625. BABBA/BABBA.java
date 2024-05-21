import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        br.close();
        long[][] AB = new long[2][2];
        AB[0][0] = 1;
        while (K-- > 0) {
            AB[1][0] = AB[0][1];
            AB[1][1] = AB[0][0] + AB[0][1];
            AB[0][0] = AB[1][0];
            AB[0][1] = AB[1][1];
        }
        System.out.println(AB[1][0] + " " + AB[1][1]);
    }
}