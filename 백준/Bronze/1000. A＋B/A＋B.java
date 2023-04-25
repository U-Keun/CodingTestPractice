import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] fingers = br.readLine().split(" ");
        int N = Integer.parseInt(fingers[0]);
        int M = Integer.parseInt(fingers[1]);
        System.out.println(N + M);

    }
}