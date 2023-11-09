import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static class Time {
        int hour, minute, second;
        void ticking() {
            second++;
            if (second == 60) {
                second = 0;
                minute++;
            }
            if (minute == 60) {
                minute = 0;
                hour++;
            }
        }
        boolean hasNumber(int k) {
            String number = String.valueOf(k);
            if (containsNumber(hour, number) ||
                    containsNumber(minute, number) ||
                    containsNumber(second, number)) return true;
            return false;
        }
        private boolean containsNumber(int i, String number) {
            if (i < 10 && number.equals("0")) return true;
            return String.valueOf(i).contains(number);
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            Time time = new Time();
            int count = 0;
            while (time.hour < N + 1) {
                if (time.hasNumber(K)) count++;
                time.ticking();
            }
            bw.write(String.valueOf(count));
            bw.flush();
        }
    }
}