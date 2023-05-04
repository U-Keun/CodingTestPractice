import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        if (isAscending(input)) System.out.println("ascending");
        else if (isDescending(input)) System.out.println("descending");
        else System.out.println("mixed");
    }
    static boolean isAscending(String[] seq) {
        boolean answer = true;
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                if (Integer.parseInt(seq[i]) != 1) {
                    answer = false;
                    break;
                }
            } else {
                if (Integer.parseInt(seq[i]) - Integer.parseInt(seq[i - 1]) != 1) {
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }
    static boolean isDescending(String[] seq) {
        boolean answer = true;
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                if (Integer.parseInt(seq[i]) != 8) {
                    answer = false;
                    break;
                }
            } else {
                if (Integer.parseInt(seq[i]) - Integer.parseInt(seq[i - 1]) != -1) {
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }
}