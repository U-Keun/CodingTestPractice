import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String[] splitInput;
        int a, b;
        while ( !(input = br.readLine()).equals("0 0") ) {
            splitInput = input.split(" ");
            a = Integer.parseInt(splitInput[0]);
            b = Integer.parseInt(splitInput[1]);
            System.out.println(a + b);
        }
    }
}