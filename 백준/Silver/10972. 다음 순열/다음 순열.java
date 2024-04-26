import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] permutation = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        int pointer = N - 1;
        Deque<Integer> queue = new ArrayDeque<>();
        while (pointer > 0) {
            if (permutation[pointer - 1] < permutation[pointer]) break;
            queue.addLast(permutation[pointer]);
            pointer--;
        }
        queue.addLast(permutation[pointer]);

        if (queue.size() == N) {
            System.out.println(-1);
            return;
        }

        int numberToMove = permutation[pointer - 1];
        Deque<Integer> subQueue = new ArrayDeque<>();
        while (numberToMove > queue.peekFirst()) {
            subQueue.addLast(queue.pollFirst());
        }
        permutation[pointer - 1] = queue.pollFirst();
        while (!subQueue.isEmpty()) {
            permutation[pointer++] = subQueue.pollFirst();
        }
        permutation[pointer++] = numberToMove;
        while (!queue.isEmpty()) {
            permutation[pointer++] = queue.pollFirst();
        }

        String result = Arrays.stream(permutation)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}