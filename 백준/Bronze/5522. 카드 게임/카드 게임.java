import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long number = 0;
        for (int i = 0; i < 5; i++) {
            long input = Long.parseLong(br.readLine());
            number += input;
        }
        System.out.println(number);
    }
}