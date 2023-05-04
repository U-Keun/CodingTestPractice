import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sentence = br.readLine().replace('-', ' ');
        String[] splitSentence = sentence.split(" ");
        ArrayList<String> words = new ArrayList<>();
        int mark;
        for (String s:splitSentence) {
            mark = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\'' ) {
                    if (isOmitted(s.substring(mark, i)) && isVowel(s.charAt(i + 1))) {
                        words.add(s.substring(mark, i));
                        mark = i + 1;
                    }
                }
            }
            words.add(s.substring(mark));
        }
        System.out.println(words.size());
    }
    static boolean isVowel(char s) {
        char[] vowels = { 'a', 'i', 'u', 'e', 'o', 'h' };
        for (char c:vowels) {
            if (c == s) return true;
        }
        return false;
    }
    static boolean isOmitted(String s) {
        String[] articles = { "c", "j", "n", "m", "t", "s", "l", "d", "qu" };
        for (String t:articles) {
            if (t.equals(s)) return true;
        }
        return false;
    }
}