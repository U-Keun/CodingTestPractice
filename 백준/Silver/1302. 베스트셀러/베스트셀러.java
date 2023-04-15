import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> books = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            books.add(br.readLine());
        }
        TreeSet<String> set = new TreeSet<>(books);
        String answer = "";
        int max = 0;
        for (String str: set) {
            if (Collections.frequency(books, str) > max) {
                max = Collections.frequency(books, str);
                answer = str;
            }
        }
        System.out.println(answer);
    }
}