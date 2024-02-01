import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        int count = 0, number = 1;
        while (S > 0) {
            S -= number++;
            count++;
        }
        if (S != 0) {
            System.out.println(count - 1);
        } else {
            System.out.println(count);
        }
    }
}