import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        String input = br.readLine();
        int answer = 0;
        for (int i = 0; i < L; i++) {
            answer += (input.charAt(i) - 'a' + 1) * power(31, i);
            answer = answer % 1234567891;
        }
        System.out.println(answer);
   }
    static int power(int n, int k) {
        int prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= n;
            prod = prod % 1234567891;
            if (prod < 0) prod += 1234567891;
        }
        return prod;
    }
}