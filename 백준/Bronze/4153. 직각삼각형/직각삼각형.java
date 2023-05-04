import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String[] numbers;
        int x, y, z, tmp;
        while (!(input = br.readLine()).equals("0 0 0")) {
            numbers = input.split(" ");
            x = Integer.parseInt(numbers[0]);
            y = Integer.parseInt(numbers[1]);
            z = Integer.parseInt(numbers[2]);
            if (x > z) {
                tmp = x;
                x = z;
                z = tmp;
            }
            if (y > z) {
                tmp = y;
                y = z;
                z = tmp;
            }
            if (x * x + y * y == z * z) System.out.println("right");
            else System.out.println("wrong");
        }
    }
}