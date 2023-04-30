import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String word = br.readLine();
        String input;
        int answer = 0;
        Loop: for (int i = 0; i < n; i++) {
            input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == word.charAt(0)) {
                    String subInput = input.substring(j);
                    String subWord = word.substring(1);
                    int dist = 1;
                    while (dist * subWord.length() < subInput.length()) {
                        StringBuilder makeWord = new StringBuilder();
                        makeWord.append(word.charAt(0));
                        for (int k = 1; k <= subWord.length(); k++) {
                            makeWord.append(subInput.charAt(dist * k));
                        }
                        if (makeWord.toString().equals(word)) {
                            answer++;
                            continue Loop;
                        }
                        dist++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}