import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        boolean[] check;
        String[] splitInput;
        String input;
        while((input = br.readLine()) != null) {
            splitInput = input.split(" ");
            check = new boolean[]{check1(splitInput), check2(splitInput), check3(splitInput), check4(splitInput), check5(splitInput)};
            print.append("form ");
            ArrayList<Integer> errors = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                if (!check[i]) {
                    errors.add(i + 1);
                }
            }
            if (errors.size() == 0) print.append("ok");
            else {
                if (errors.size() == 1) print.append("error ");
                else print.append("errors ");
                for (int i = 0; i < errors.size(); i++) {
                    if (i == 0) print.append(errors.get(i));
                    else if (i != errors.size() - 1) print.append(", " + errors.get(i));
                    else print.append(" and " + errors.get(i));
                }
            }
            print.append(": ");
            for (String s : splitInput) {
                print.append(s + " ");
            }
            print.append('\n');

        }
        System.out.println(print);
    }

    static boolean check1(String[] input) {
        int n = input.length;
        boolean answer = true;
        for (int i = 0; i < n; i++) {
            if (input[i].equals("dip")) {
                if (i == 0) {
                    if (i == n - 1 || !input[i + 1].equals("twirl")) {
                        answer = false;
                        input[i] = "DIP";
                    }
                } else if (i == 1) {
                    if (!input[i - 1].equals("jiggle") && (i == n - 1 || !input[i + 1].equals("twirl"))) {
                        answer = false;
                        input[i] = "DIP";
                    }
                } else if (i == n - 1) {
                    if (!input[i - 2].equals("jiggle") && !input[i - 1].equals("jiggle")) {
                        answer = false;
                        input[i] = "DIP";
                    }
                } else {
                    if (!input[i - 2].equals("jiggle") && !input[i - 1].equals("jiggle") && !input[i + 1].equals("twirl")) {
                        answer = false;
                        input[i] = "DIP";
                    }
                }
            }
        }
        return answer;
    }
    static boolean check2(String[] input) {
        int n = input.length;
        boolean answer = true;
        if (input.length < 3) return false;
        if (!input[n - 3].equals("clap") || !input[n - 2].equals("stomp") || !input[n - 1].equals("clap")) answer = false;
        return answer;
    }
    static boolean check3(String[] input) {
        boolean twirl = false;
        boolean hop = false;
        for (String s:input) {
            if (s.equals("twirl")) twirl = true;
            else if (s.equals("hop")) hop = true;

            if (twirl && hop) {
                break;
            }
        }
        if (twirl) {
            return hop;
        } else return true;
    }
    static boolean check4(String[] input) {
        return !input[0].equals("jiggle");
    }
    static boolean check5(String[] input) {
        boolean answer = false;
        for (String s:input) {
            if (s.equalsIgnoreCase("dip")) {
                answer = true;
                break;
            }
        }
        return answer;
    }
}