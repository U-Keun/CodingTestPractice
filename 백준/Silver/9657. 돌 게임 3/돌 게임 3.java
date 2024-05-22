import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        boolean[] isSKWin = new boolean[1000];
        isSKWin[0] = true;
        isSKWin[2] = true;
        isSKWin[3] = true;
        for (int i = 4; i < N; i++) {
            if (!isSKWin[i - 1] || !isSKWin[i - 3] || !isSKWin[i - 4]) {
                isSKWin[i] = true;
            }
        }
        if (isSKWin[N - 1]) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}