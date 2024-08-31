import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Stack<String> pth = new Stack<>();
        Loop1 : for (int i=0; i < N; i++) {
            String[] pthseq = br.readLine().split("");
            for (String p : pthseq) {
                if (p.equals("(")) {
                    pth.push(p);
                } else {
                    if (pth.isEmpty()) {
                        print.append("NO").append('\n');
                        continue Loop1;
                    } else {
                        pth.pop();
                    }
                }
            }
            if (pth.size() > 0) {
                print.append("NO").append('\n');
                pth.clear();
            } else print.append("YES").append('\n');
        }
        System.out.println(print);
    }
}