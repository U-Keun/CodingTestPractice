import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String input;
    static int i;
    public static void main(String[] args) throws IOException {
        input = br.readLine();
        i = Integer.parseInt(br.readLine());
        System.out.println(input.charAt(i - 1));
    }
}