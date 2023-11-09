import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = parsing(st.nextToken()), B = parsing(st.nextToken()), C = parsing(st.nextToken()), M = parsing(st.nextToken());
            int time = 24, present = 0, work = 0;
            while (time-- > 0) {
                if (present + A <= M) {
                    work += B;
                    present += A;
                } else {
                    present = Math.max(0, present - C);
                }
            }
            bw.write(String.valueOf(work));
            bw.flush();
        }
    }
    private static Integer parsing(String number) {
        return Integer.parseInt(number);
    }
}