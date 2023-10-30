import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            List<String> answer = recurrence(N);
            for (String s:answer) {
                bw.write(s);
                bw.write("\n");
            }
            bw.flush();
        }
    }
    private static List<String> recurrence(int N) {
        List<String> answer = new ArrayList<>();
        if (N == 3) {
            answer.add("  *  ");
            answer.add(" * * ");
            answer.add("*****");
            return answer;
        }
        List<String> prev = recurrence(N / 2);
        String blank = " ".repeat(N / 2);
        for (String s:prev) {
            StringBuilder sb = new StringBuilder();
            sb.append(blank).append(s).append(blank);
            answer.add(sb.toString());
        }
        for (String s:prev) {
            StringBuilder sb = new StringBuilder();
            sb.append(s).append(" ").append(s);
            answer.add(sb.toString());
        }
        return answer;
    }
}