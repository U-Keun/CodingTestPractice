import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nd = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        int count = 0;
        for (int i = 1; i <= nd[0]; i++) {
            int number = i;
            while (number > 0) {
                if (number % 10 == nd[1]) {
                    count++;
                }
                number /= 10;
            }
        }
        System.out.println(count);
    }
}