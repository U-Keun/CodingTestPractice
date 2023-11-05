import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static class Bulbs {
        boolean[] status;
        Bulbs(String[] input) {
            status = new boolean[input.length];
            int idx = 0;
            for (String s:input){
                if (s.equals("1")) {
                    status[idx] = true;
                }
                idx++;
            }
        }
        void commandOne(int idx, int status) {
            this.status[idx - 1] = status == 1;
        }
        void commandTwo(int left, int right) {
            for (int i = left - 1; i < right; i++) {
                this.status[i] = !this.status[i];
            }
        }
        void commandThree(int left, int right) {
            for (int i = left - 1; i < right; i++) {
                this.status[i] = false;
            }
        }
        void commandFour(int left, int right) {
            for (int i = left - 1; i < right; i++) {
                this.status[i] = true;
            }
        }
        public String toString() {
            StringBuilder print = new StringBuilder();
            for (boolean b:status) {
                print.append((b)? 1:0).append(" ");
            }
            return print.toString();
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
            Bulbs bulbs = new Bulbs(br.readLine().split(" "));
            for (int i = 0; i < M; i++) {
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                int c = Integer.parseInt(input[2]);
                switch (a) {
                    case 1:
                        bulbs.commandOne(b, c);
                        break;
                    case 2:
                        bulbs.commandTwo(b, c);
                        break;
                    case 3:
                        bulbs.commandThree(b, c);
                        break;
                    case 4:
                        bulbs.commandFour(b, c);
                        break;
                }
            }
            bw.write(bulbs.toString());
            bw.flush();
        }
    }
}