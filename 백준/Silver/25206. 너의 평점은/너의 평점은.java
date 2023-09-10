import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String input;
        float totalSum = 0f, creditSum = 0f;
        while ((input = br.readLine()) != null) {
            String[] split = input.split(" ");
            float credit = Float.parseFloat(split[1]);
            switch (split[2]) {
                case "A+":
                    totalSum += credit * 4.5;
                    break;
                case "A0":
                    totalSum += credit * 4.0;
                    break;
                case "B+":
                    totalSum += credit * 3.5;
                    break;
                case "B0":
                    totalSum += credit * 3.0;
                    break;
                case "C+":
                    totalSum += credit * 2.5;
                    break;
                case "C0":
                    totalSum += credit * 2.0;
                    break;
                case "D+":
                    totalSum += credit * 1.5;
                    break;
                case "D0":
                    totalSum += credit * 1.0;
                    break;
                case "P":
                    continue;
            }
            creditSum += credit;
        }
        br.close();
        bw.write((totalSum / creditSum) + "\n");
        bw.flush();
        bw.close();
    }
}