import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String reversed;
        while (!(input = br.readLine()).equals("0")) {
            reversed = "";
            for (int i = input.length() - 1; i >= 0; i--) {
                reversed += input.charAt(i);
            }
            if (input.equals(reversed)) System.out.println("yes");
            else System.out.println("no");
        }
    }
}