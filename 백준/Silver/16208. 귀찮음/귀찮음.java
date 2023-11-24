import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Long answer = 0L;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Long> numbers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                numbers.add(Long.parseLong(st.nextToken()));
            }
            Collections.sort(numbers);
            setCost(numbers);
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
    private static void setCost(List<Long> numbers) {
        int l = numbers.size();
        if (l == 1) {
            return;
        }
        if (l == 2) {
            answer += numbers.get(0) * numbers.get(1);
            return;
        }
        List<Long> head = numbers.subList(0, (l + 1) / 2);
        List<Long> tail = numbers.subList((l + 1) / 2, l);
        answer += sum(head) * sum(tail);
        setCost(head);
        setCost(tail);
    }
    private static Long sum(List<Long> numbers) {
        return numbers.stream()
                .reduce(0L, Long::sum);
    }
}