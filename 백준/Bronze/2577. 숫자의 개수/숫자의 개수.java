import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());
        String calc = A * B * C + "";
        int[] counts = new int[10];
        for (char n:calc.toCharArray()) {
            counts[n - '0']++;
        }
        for (int i:counts) System.out.println(i);
    }
}