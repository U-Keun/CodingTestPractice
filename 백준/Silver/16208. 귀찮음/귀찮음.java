import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Integer answer = 0;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }
            setCost(numbers);
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
    private static void setCost(List<Integer> numbers) {
        int l = numbers.size();
        if (l == 1) {
            return;
        }
        if (l == 2) {
            answer += numbers.get(0) * numbers.get(1);
            return;
        }
        List<Integer> head = numbers.subList(0, (l + 1) / 2);
        List<Integer> tail = numbers.subList((l + 1) / 2, l);
        answer += sum(head) * sum(tail);
        setCost(head);
        setCost(tail);
    }
    private static Integer sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}