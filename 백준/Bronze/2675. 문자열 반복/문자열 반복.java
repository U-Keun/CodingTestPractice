import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int repeat;
        String word;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            repeat = Integer.parseInt(st.nextToken());
            word = st.nextToken();
            for (char c:word.toCharArray()) {
                print.append(String.valueOf(c).repeat(Math.max(0, repeat)));
            }
            print.append('\n');
        }
        System.out.println(print);
    }
}