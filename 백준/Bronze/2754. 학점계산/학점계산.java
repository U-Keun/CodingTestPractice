import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String[] category = {"F", "D", "C", "B", "A"}, spec = {"+", "0", "-"};
    public static void main(String[] args) throws IOException {
        Map<String, Float> score = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                score.put(category[i], 0f);
                continue;
            }
            for (int j = 0; j < 3; j++) {
                switch (spec[j]) {
                    case "+":
                        score.put(category[i] + spec[j], i + 0.3f);
                        break;
                    case "0":
                        score.put(category[i] + spec[j], (float) i);
                        break;
                    case "-":
                        score.put(category[i] + spec[j], i - 0.3f);
                }
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();
        System.out.printf("%.1f", score.get(input));
    }
}