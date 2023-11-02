import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Integer> firstQueue = new LinkedList<>();
            Queue<Integer> secondQueue = new LinkedList<>();
            Stack<Integer> thirdStack = new Stack<>();
            for (int i = N; i > 0; i--) {
                switch (Integer.parseInt(st.nextToken())) {
                    case 1:
                        firstQueue.add(i);
                        while (!secondQueue.isEmpty()) {
                            firstQueue.add(secondQueue.poll());
                        }
                        break;
                    case 2:
                        secondQueue.add(i);
                        break;
                    case 3:
                        thirdStack.push(i);
                        break;
                }
            }
            StringBuilder print = new StringBuilder();
            while (!firstQueue.isEmpty()) {
                print.append(firstQueue.poll()).append(" ");
            }
            while (!thirdStack.isEmpty()) {
                print.append(thirdStack.pop()).append(" ");
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}