import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean possible;
    static String S, T;

    public static void main(String[] args) throws IOException {
        S = br.readLine();
        T = br.readLine();
        recurrence(T);
        if (possible) System.out.println(1);
        else System.out.println(0);
    }
    static void recurrence(String t) {
        if (t.length() == S.length()) {
            if (t.equals(S)) possible = true;
            return;
        }
        if (t.charAt(t.length() - 1) == 'A') {
            recurrence(t.substring(0, t.length() - 1));
        }
        if (t.charAt(0) == 'B') {
            String newStr = "";
            for (int i = t.length() - 1; i > 0; i--) {
                newStr += t.charAt(i);
            }
            recurrence(newStr);
        }
    }
}