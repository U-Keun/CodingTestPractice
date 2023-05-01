import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = 0;
        while (st.hasMoreTokens()) {
            k += Math.pow(Integer.parseInt(st.nextToken()),2);
        }
        System.out.println(k % 10);
    }
}