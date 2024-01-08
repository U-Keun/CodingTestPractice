import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        for (Map<Character, Integer> characterIntegerMap : Arrays.asList(
                Map.of('A', 3, 'B', 2, 'C', 1, 'D', 2, 'E', 3, 'F', 3, 'G', 3, 'H', 3, 'I', 1, 'J', 1),
                Map.of('K', 3, 'L', 1, 'M', 3, 'N', 3, 'O', 1, 'P', 2, 'Q', 2, 'R', 2, 'S', 1, 'T', 2),
                Map.of('U', 1, 'V', 1, 'W', 2, 'X', 2, 'Y', 2, 'Z', 1))) {
            map.putAll(characterIntegerMap);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();
        int number = 0;
        for (char c : input.toCharArray()) {
            number += map.get(c);
        }
        if ((number % 10) % 2 == 0)
            System.out.println("You're the winner?");
        else {
            System.out.println("I'm a winner!");
        }
    }
}