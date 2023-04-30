import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] frequency = new int[26];
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') frequency[input.charAt(i) - 'a']++;
            else frequency[input.charAt(i) - 'A']++;
        }
        int first = 0, second = 0;
        int firstIndex = 0;
        for (int j = 0; j < frequency.length; j++) {
            if (frequency[j] > first) {
                second = first;
                first = frequency[j];
                firstIndex = j;
            } else if (frequency[j] > second) {
                second = frequency[j];
            }
        }
        if (first == second) System.out.println("?");
        else System.out.println((char)('A' + firstIndex));
    }
}