import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[26];
        for (int i = 0; i< 26; i++) array[i] = -1;
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            if (array[(int) input.charAt(i) - 'a'] == -1) array[(int) input.charAt(i) - 'a'] = i;
        }
        for (int i:array) {
            System.out.print(i + " ");
        }
    }
}