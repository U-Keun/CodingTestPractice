import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static class Egg {
        int durability, weight;
        Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
        boolean isBroken() {
            return durability <= 0;
        }
        void isShockedByWeight(int weight) {
            this.durability -= weight;
        }
    }
    private static int N, answer;
    private static Egg[] eggs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            int[] eggInfo = readIntegers(br.readLine());
            eggs[i] = new Egg(eggInfo[0], eggInfo[1]);
        }
        answer = 0;
        backtracking(0);
        System.out.println(answer);
    }
    private static void backtracking(int current) {
        if (current == N) {
            answer = Math.max(answer, countBrokenEggs());
            return;
        }
        if (eggs[current].isBroken()) {
            backtracking(current + 1);
        } else {
            for (int i = 0; i < N; i++) {
                if (current == i || eggs[i].isBroken()) continue;
                eggs[current].isShockedByWeight(eggs[i].weight);
                eggs[i].isShockedByWeight(eggs[current].weight);
                backtracking(current + 1);
                eggs[current].isShockedByWeight(-eggs[i].weight);
                eggs[i].isShockedByWeight(-eggs[current].weight);
            }
            answer = Math.max(answer, countBrokenEggs());
        }
    }
    private static int countBrokenEggs() {
        int count = 0;
        for (Egg egg : eggs) {
            if (egg.isBroken()) count++;
        }
        return count;
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}