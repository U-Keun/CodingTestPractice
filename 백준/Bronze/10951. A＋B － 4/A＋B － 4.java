import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A, B;
        String input;
        while((input = br.readLine()) != null) {
            String[] numbers = input.split(" ");
            A = Integer.parseInt(numbers[0]);
            B = Integer.parseInt(numbers[1]);

            System.out.println(A + B);
        }
    }
}