import java.io.IOException;

public class Main {
    private static final String format = "Cube = %d, Triple = (%d,%d,%d)\n";
    public static void main(String[] args) throws IOException {
        StringBuilder print = new StringBuilder();
        int a = 2;
        while (a <= 100) {
            int target = a * a * a;
            int b = 2;
            while (target > b * b * b) {
                int secondTarget = target - b * b * b;
                int c = b;
                while (secondTarget > c * c * c) {
                    int thirdTarget = secondTarget - c * c * c;
                    int d = c;
                    while (thirdTarget >= d * d * d) {
                        int fourthTarget = thirdTarget - d * d * d;
                        if (fourthTarget == 0) {
                            print.append(String.format(format, a, b, c, d));
                        }
                        d++;
                    }
                    c++;
                }
                b++;
            }
            a++;
        }
        System.out.println(print);
    }
}