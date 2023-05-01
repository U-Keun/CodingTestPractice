import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if ((N % 100 != 0 && N % 4 == 0) || N % 400 == 0) System.out.println(1);
        else System.out.println(0);
    }
}