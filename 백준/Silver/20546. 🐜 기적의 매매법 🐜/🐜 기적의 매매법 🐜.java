import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int seedMoney = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int bnpStock = 0, timingStock = 0, bnpMoney = seedMoney, timingMoney = seedMoney;
            int incStack = 0, decStack = 0;
            Integer prev = null;
            for (int i = 0; i < 14; i++) {
                int price = Integer.parseInt(st.nextToken());
                if (bnpMoney >= price) {
                    bnpStock += bnpMoney / price;
                    bnpMoney = bnpMoney % price;
                }
                if (prev != null) {
                    if (price > prev) {
                        incStack++;
                        decStack = 0;
                    } else if (price < prev) {
                        decStack++;
                        incStack = 0;
                    }

                    if (incStack >= 3) {
                        timingMoney += timingStock * price;
                        timingStock = 0;
                    } else if (decStack >= 3) {
                        timingStock += timingMoney / price;
                        timingMoney = timingMoney % price;
                    }
                }
                prev = price;
            }
            int bnpValue = bnpMoney + prev * bnpStock, timingValue = timingMoney + prev * timingStock;
            if (bnpValue > timingValue) {
                bw.write("BNP");
            } else if (bnpValue < timingValue) {
                bw.write("TIMING");
            } else bw.write("SAMESAME");
            bw.flush();
        }
    }
}