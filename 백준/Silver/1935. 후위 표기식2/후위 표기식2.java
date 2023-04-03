import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] equation = br.readLine().split("");
        Map<Character, Integer> subs = new HashMap<>();
        for (char c='A'; c<'A'+N; c++) {
            subs.put(c,Integer.parseInt(br.readLine()));
        }
        Stack<Double> calc = new Stack<>();
        for (String s:equation){
            char c = s.charAt(0);
            if (c >= 'A' && c <= 'Z') {
                calc.push(Double.valueOf(subs.get(c)));
            } else {
                switch (c) {
                    case '+':
                        double p1 = calc.pop();
                        double p2 = calc.pop();
                        calc.push(p1 + p2);
                        break;
                    case '-':
                        double m1 = calc.pop();
                        double m2 = calc.pop();
                        calc.push(m2 - m1);
                        break;
                    case '*':
                        double t1 = calc.pop();
                        double t2 = calc.pop();
                        calc.push(t1 * t2);
                        break;
                    case '/':
                        double d1 = calc.pop();
                        double d2 = calc.pop();
                        calc.push((Math.round(d2/d1*100)/100.0));
                        break;
                }
            }
        }
        System.out.printf("%.2f",calc.pop());
    }
}