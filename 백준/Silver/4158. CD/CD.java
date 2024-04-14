import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = readIntegers(br.readLine());
        StringBuilder print = new StringBuilder();
        while (NM[0] != 0 || NM[1] != 0) {
            Deque<Integer> SK = new ArrayDeque<>();
            int index;
            for (int i = 0; i < NM[0]; i++) {
                SK.add(Integer.parseInt(br.readLine()));
            }
            int answer = 0;
            for (int i = 0; i < NM[1]; i++) {
                index = Integer.parseInt(br.readLine());
                while (!SK.isEmpty() && SK.peek() < index) {
                    SK.poll();
                }
                if (!SK.isEmpty() && SK.peek() == index) {
                    answer++;
                    SK.poll();
                }
            }
            print.append(answer).append("\n");
            NM = readIntegers(br.readLine());
        }
        br.close();
        System.out.println(print);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}