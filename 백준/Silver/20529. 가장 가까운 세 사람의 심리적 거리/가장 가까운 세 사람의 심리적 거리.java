import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                int N = Integer.parseInt(br.readLine());
                StringTokenizer st = new StringTokenizer(br.readLine());
                if (N > 32) {
                    bw.write("0\n");
                    continue;
                }
                HashMap<String, Integer> input = new HashMap<>();
                for (int j = 0; j < N; j++) {
                    String s = st.nextToken();
                    input.putIfAbsent(s, 0);
                    input.replace(s, input.get(s) + 1);
                }
                LinkedList<LinkedList<String>> combinations = mbtiCombination(input, 3);
                int min = Integer.MAX_VALUE;
                for (LinkedList<String> c:combinations) {
                    min = Math.min(min, distance(c));
                }
                bw.write(String.valueOf(min));
                bw.write("\n");
            }
            bw.flush();
        }
    }
    private static LinkedList<LinkedList<String>> mbtiCombination(HashMap<String, Integer> input, int r) {
        LinkedList<LinkedList<String>> answer = new LinkedList<>();
        if (r == 0) {
            answer.add(new LinkedList<>());
            return answer;
        }
        LinkedList<LinkedList<String>> tmp;
        List<String> keys = new ArrayList<>(input.keySet());
        for (int i = 0; i < keys.size(); i++) {
            String s = keys.get(i);
            if (input.get(s) > 0) {
                input.replace(s, input.get(s) - 1);
                LinkedHashMap<String, Integer> subInput = new LinkedHashMap<>();
                for (int j = i; j < keys.size(); j++) {
                    subInput.put(keys.get(j), input.get(keys.get(j)));
                }
                tmp = mbtiCombination(subInput, r - 1);

                for (LinkedList<String> list:tmp) {
                    list.addFirst(s);
                    answer.add(list);
                }
                input.replace(s, input.get(s) + 1);
            }
        }
        return answer;
    }
    private static int distance(LinkedList<String> list) {
        int answer = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                String a = list.get(i), b = list.get(j);
                for (int k = 0; k < 4; k++) if (a.charAt(k) != b.charAt(k)) answer++;
            }
        }
        return answer;
    }
}