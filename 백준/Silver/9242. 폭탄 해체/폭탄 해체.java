import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int n;
        String[] stringInputs;
        String digit;
        input = br.readLine();
        n = (input.length() + 1) / 4;
        String[] numbers = new String[n];
        for (int j = 0; j < n; j++) {
            digit = (j == n - 1)?input.substring(j * 4):input.substring(j * 4, j * 4 + 3);
            numbers[j] = digit;
        }
        for (int i = 0; i < 4; i++) {
            input = br.readLine();
            for (int j = 0; j < n; j++) {
                digit = (j == n - 1)?input.substring(j * 4):input.substring(j * 4, j * 4 + 3);
                numbers[j] += digit;
            }
        }
        int code = 0;
        String answer = "";
        for (int k = 0; k < n; k++) {
            int t = checkNumber(numbers[k]);
            if (t != -1) code += checkNumber(numbers[k]) * (int)Math.pow(10,n - k - 1);
            else {
                answer = "BOOM!!";
                break;
            }
        }

        if (!answer.equals("")) System.out.println(answer);
        else {
            if (code % 6 == 0) answer = "BEER!!";
            else answer = "BOOM!!";
            System.out.println(answer);
        }
    }
    static int checkNumber(String string) {
        int number;
        switch (string) {
            case "**** ** ** ****":
                number = 0;
                break;
            case "  *  *  *  *  *":
                number = 1;
                break;
            case "***  *****  ***":
                number = 2;
                break;
            case "***  ****  ****":
                number = 3;
                break;
            case "* ** ****  *  *":
                number = 4;
                break;
            case "****  ***  ****":
                number = 5;
                break;
            case "****  **** ****":
                number = 6;
                break;
            case "***  *  *  *  *":
                number = 7;
                break;
            case "**** ***** ****":
                number = 8;
                break;
            case "**** ****  ****":
                number = 9;
                break;
            default:
                number = -1;
                break;
        }
        return number;
    }
}