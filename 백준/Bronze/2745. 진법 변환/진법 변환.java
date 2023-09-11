import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        br.close();
        char[] number = input[0].toCharArray();
        int B = Integer.parseInt(input[1]), value = 0, n = number.length - 1;
        for (char c:number) {
            if (c >= '0' && c <= '9') value += (c - '0') * Math.pow(B, n--);
            else value += (c - 'A' + 10) * Math.pow(B, n--);
        }
        bw.write(value + "\n");
        bw.flush();
        bw.close();
    }
}