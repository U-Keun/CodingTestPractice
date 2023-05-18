import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int k;
        Set<Integer> numbers = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            k = Integer.parseInt(br.readLine());
            numbers.add(k);
        }
        for (int i:numbers) print.append(i).append('\n');
        System.out.println(print);
    }
}