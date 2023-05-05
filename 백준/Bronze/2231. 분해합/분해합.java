import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (isConstructor(N, i)) answer = i;
        }
        System.out.println(answer);
    }
    static boolean isConstructor(int N, int i) {
        int calc = i;
        String s = String.valueOf(i);
        for (int j = 0; j < s.length(); j++) {
            calc += s.charAt(j) - '0';
        }
        return N == calc;
    }
}