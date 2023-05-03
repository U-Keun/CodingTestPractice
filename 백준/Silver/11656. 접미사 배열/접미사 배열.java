import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        TreeSet<String> answer = new TreeSet<>();
        for (int i = 0; i < input.length(); i++) {
            answer.add(input.substring(i));
        }
        for (String s:answer) {
            System.out.println(s);
        }
    }
}