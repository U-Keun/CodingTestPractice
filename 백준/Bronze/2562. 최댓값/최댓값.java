import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxVal = 0, idx = 0;
        int input;
        for (int i = 0; i < 9; i++) {
            input = Integer.parseInt(br.readLine());
            if (input > maxVal) {
                maxVal = input;
                idx = i + 1;
            }
        }
        System.out.println(maxVal);
        System.out.println(idx);
    }
}