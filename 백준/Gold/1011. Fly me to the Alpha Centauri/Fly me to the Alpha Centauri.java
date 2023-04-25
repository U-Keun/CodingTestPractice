import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int a;
        String b;
        Scanner scanner = new Scanner(System.in);
        a = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < a; i++){
            b = scanner.nextLine();
            double d = Double.parseDouble(b.split(" ")[1]) - Double.parseDouble(b.split(" ")[0]);
            int answer;
            int diag = (int)Math.sqrt(d);
            if (Math.pow(diag, 2) == d) answer = 2 * diag - 1;
            else if (diag * (diag + 1) < d) answer = 2 * diag + 1;
            else answer = 2 * diag;
            System.out.println(answer);
        }
    }
}