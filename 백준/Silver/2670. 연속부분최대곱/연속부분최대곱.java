import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        double[] numbers = new double[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Double.parseDouble(br.readLine());
        }
        double result = 0;
        double record;
        for (int i = 0; i < N; i++) {
            record = 1;
            for (int j = i; j < N; j++) {
                record *= numbers[j];
                result = Math.max(result, record);
            }
        }
        System.out.printf("%.3f", result);
    }
}