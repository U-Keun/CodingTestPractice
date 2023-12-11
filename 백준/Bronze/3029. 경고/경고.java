import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] start = br.readLine().split(":"), end = br.readLine().split(":");
            int[] time = new int[3];
            for (int i = 0; i < 3; i++) {
                time[i] = Integer.parseInt(end[i]) - Integer.parseInt(start[i]);
                if (i == 0 && time[i] < 0) {
                    time[i] += 24;
                } else if (time[i] < 0) {
                    int j = i;
                    while (j > 0) {
                        time[j - 1]--;
                        time[j--] += 60;
                        if (time[j] >= 0) break;
                    }
                }
            }
            if (time[0] < 0 || (time[0] == 0 && time[1] == 0 && time[2] == 0)) time[0] += 24;
            bw.write(String.format("%02d:%02d:%02d", time[0], time[1], time[2]));
            bw.flush();
        }
    }
}