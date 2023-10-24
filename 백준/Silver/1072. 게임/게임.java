import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] input = br.readLine().split(" ");
            long X = Long.parseLong(input[0]), Y = Long.parseLong(input[1]);
            long Z = Y * 100 / X;
            if (Z >= 99) {
                bw.write(String.valueOf(-1));
                bw.flush();
                return;
            }
            int low = 1, high = (int) X;
            while (low <= high) {
                int mid = (low + high) >> 1;
                if ((Y + mid) * 100 / (X + mid) > Z) high = mid - 1;
                else low = mid + 1;
            }
            bw.write(String.valueOf(low));
            bw.flush();
        }
    }
}