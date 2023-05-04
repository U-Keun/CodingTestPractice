import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String A = input[0], B = input[1];
        int minSysA = availableSys(A);
        int minSysB = availableSys(B);
        Long[] listA = candidateList(A);
        Long[] listB = candidateList(B);

        Map<int[], Long> answer = new HashMap<>();

        for (int i = minSysA; i < 37; i++) {
            Long candidate = listA[i - minSysA];
            if (candidate == null) continue;
            for (int j = minSysB; j < 37; j++) {
                if (i == j) continue;
                if (Objects.equals(candidate, listB[j - minSysB])) {
                    int[] syss = new int[2];
                    syss[0] = i;
                    syss[1] = j;
                    answer.put(syss, candidate);
                }
            }
        }
        if (answer.size() > 1) System.out.println("Multiple");
        else if (answer.size() < 1) System.out.println("Impossible");
        else {
            for (int[] number:answer.keySet()) {
                System.out.println(answer.get(number) + " " + number[0] + " " + number[1]);
            }
        }
    }
    static int availableSys(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a') {
                if (s.charAt(i) - 'a' + 10 > answer) {
                    answer = s.charAt(i) - 'a' + 10;
                }
            } else {
                if (s.charAt(i) - '0' > answer) answer = s.charAt(i) - '0';
            }
        }
        if (answer == 0) return 2;
        return answer + 1;
    }
    static double log2(int x) {
        return Math.log10(x) / Math.log10(2);
    }
    static Long[] candidateList(String A) {
        Long calc = 0L;
        int length;
        char c;
        int minSysA = availableSys(A);
        Long[] listA = new Long[37 - minSysA];
        for (int i = minSysA; i < 37; i++) {
            calc = 0L;
            length = A.length();
            if (length > 63 / log2(i) - 1) continue;

            for (int j = 0; j < length; j++) {
                c = A.charAt(j);
                long degree = Math.round(Math.pow(i, length - 1 - j));
                if (c >= 'a') {
                    calc += (A.charAt(j) - 'a' + 10) * degree;
                } else {
                    calc += (A.charAt(j) - '0') * degree;
                }
            }
            listA[i - minSysA] = calc;
        }
        return listA;
    }
}
