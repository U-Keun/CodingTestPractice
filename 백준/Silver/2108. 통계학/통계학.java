import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int N, max, min, input, sum;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        max = -4000;
        min = 4000;
        sum = 0;
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            sum += input;
            if (input > max) max = input;
            if (input < min) min = input;
            numbers[i] = input;
        }
        print.append(Math.round(sum / (double) N)).append('\n');
        Arrays.sort(numbers);
        print.append(numbers[N / 2]).append('\n');
        print.append(mode()).append('\n');
        print.append(max - min);
        System.out.println(print);
    }
    static int mode() {
        int k = max - min + 1;
        int[] array = new int[k];
        if (numbers.length == 1) return numbers[0];
        for (int i:numbers) {
            array[i - min]++;
        }
        int min1 = 4001, min2 = 4001, frequency = 1;
        for (int i = 0; i < k; i++) {
            if (array[i] > frequency) {
                frequency = array[i];
                min1 = i + min;
                min2 = 4001;
            } else if (array[i] == frequency) {
                if (i + min < min1) {
                    min2 = min1;
                    min1 = i + min;
                }
                else if (i + min < min2) min2 = i + min;
            }
        }
        if (min2 == 4001) return min1;
        else if (array[min1 - min] != array[min2 - min]) return min1;
        return min2;
    }
}