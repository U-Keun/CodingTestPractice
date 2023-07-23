import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] input, bomb, result;
    static int bombLength, index = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        bomb = br.readLine().toCharArray();
        bombLength = bomb.length;
        result = new char[input.length];
        for (char ch : input) {
            result[index++] = ch;
            if (index >= bombLength && checkBomb(index - bombLength)) {
                index -= bombLength;
            }
        }
        System.out.println(index == 0 ? "FRULA" : new String(result, 0, index));
    }

    static boolean checkBomb(int startIndex) {
        for (int i = 0; i < bomb.length; i++) {
            if (result[startIndex + i] != bomb[i]) {
                return false;
            }
        }
        return true;
    }
}