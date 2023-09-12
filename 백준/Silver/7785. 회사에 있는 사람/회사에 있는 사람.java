import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        TreeSet<String> attendance = new TreeSet<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken(), status = st.nextToken();
            if (status.equals("enter")) attendance.add(name);
            else attendance.remove(name);
        }
        br.close();
        int l = attendance.size();
        for (int i = 0; i < l; i++) {
            bw.write(attendance.pollLast() + "\n");
        }
        bw.flush();
        bw.close();
    }
}