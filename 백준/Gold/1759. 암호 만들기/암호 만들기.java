import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int L, C;
    private static char[] characters;
    private static final Set<Character> vowels = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));
    private static StringBuilder print = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        characters = new char[C];
        characters = br.readLine().replaceAll(" ", "").toCharArray();

        Arrays.sort(characters);

        backtracking(0, "");
        System.out.println(print);
    }
    private static void backtracking(int index, String password) {
        if (password.length() == L
            && containsAtLeastOneVowel(password) && containsAtLeastTwoConsonant(password)) {
            print.append(password).append("\n");
        }
        if (index == C) return;
        if (password.length() < L) {
            backtracking(index + 1, password + characters[index]);
            backtracking(index + 1, password);
        }
    }
    private static boolean containsAtLeastOneVowel(String password) {
        for (char c: password.toCharArray()) {
            if (vowels.contains(c)) return true;
        }
        return false;
    }
    private static boolean containsAtLeastTwoConsonant(String password) {
        return password.chars()
                .filter(c -> !vowels.contains((char) c))
                .limit(2)
                .count() >= 2;
    }
}