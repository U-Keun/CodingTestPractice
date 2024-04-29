import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] AB = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int answer = 0, addedNumber = 1, counter = 1;
        for (int i = 1; i <= 1000; i++) {
            if (i < AB[0]) {
                if (addedNumber > counter) {
                    counter++;
                } else {
                    addedNumber++;
                    counter = 1;
                }
                continue;
            }
            if (i > AB[1]) break;
            answer += addedNumber;
            if (addedNumber > counter) {
                counter++;
            } else {
                addedNumber++;
                counter = 1;
            }
        }
        System.out.println(answer);
    }
}