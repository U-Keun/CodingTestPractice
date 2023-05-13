import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> words = new TreeMap<>();
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            words.put(input, input.length());
        }
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(words.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        for (int i = 0; i < entryList.size(); i++) {
            System.out.println(entryList.get(i).getKey());
        }
    }
}