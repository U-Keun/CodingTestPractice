import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] init = br.readLine().toCharArray();
        int l = init.length;
        char[] input;
        for (int i = 1; i < N; i++) {
            input = br.readLine().toCharArray();
            for (int j = 0; j < l; j++) {
                if (init[j] != '?' && init[j] != input[j]) {
                    init[j] = '?';
                }
            }
        }
        System.out.println(new String(init));
    }
}