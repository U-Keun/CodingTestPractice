import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> tips = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tips.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(tips,Collections.reverseOrder());
        long income = 0;
        int tip;
        for (int i = 0; i < N; i++) {
            tip = tips.get(i) - i;
            if (tip > 0) income += tip;
        }
        System.out.println(income);
    }
}
