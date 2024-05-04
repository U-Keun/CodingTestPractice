import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String note = br.readLine();
        String target = br.readLine();
        br.close();
        String translate = note.replaceAll("\\d", "");
        if (translate.contains(target)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}