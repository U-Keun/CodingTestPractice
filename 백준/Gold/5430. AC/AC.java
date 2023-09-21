import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < T; i++) {
                char[] p = br.readLine().toCharArray();
                int n = Integer.parseInt(br.readLine());
                AC program = AC.create(br.readLine());
                boolean getError = false;
                for (char c:p) {
                    switch (c) {
                        case 'R': program.R(); break;
                        case 'D':
                            if (!program.D()) {
                                getError = true;
                                break;
                            }
                    }
                }
                if (getError) print.append("error");
                else print.append(program.print());
                print.append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
    static class AC {
        private Deque<Integer> array;
        private boolean isReversed;
        private AC(Deque<Integer> array, boolean isReversed) {
            this.array = array;
            this.isReversed = isReversed;
        }
        public static AC create(String input) {
            Deque<Integer> tmp = new LinkedList<>();
            if (input.equals("[]")) return new AC(tmp, false);
            String[] numbers = input.substring(1, input.length() - 1).split(",");
            for (String number : numbers) tmp.add(Integer.parseInt(number));
            return new AC(tmp, false);
        }
        public void R() { isReversed = !isReversed; }
        public boolean D() {
            if (array.isEmpty()) return false;
            if (isReversed) this.array.pollLast();
            else this.array.pollFirst();
            return true;
        }
        public String print() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (isReversed) {
                while (!array.isEmpty()) {
                    if (array.size() == 1) sb.append(array.pollLast());
                    else sb.append(array.pollLast()).append(",");
                }
            } else {
                while (!array.isEmpty()) {
                    if (array.size() == 1) sb.append(array.pollFirst());
                    else sb.append(array.pollFirst()).append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}