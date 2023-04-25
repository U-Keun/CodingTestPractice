import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Point> keyboard = new HashMap<>();
        char[] alphabets = {'q','w','e','r','t','y','u','i','o','p',
                'a','s','d','f','g','h','j','k','l',' ',
                'z','x','c','v','b','n','m'};
        int i = 0;
        for (char c:alphabets) {
            keyboard.put(c, new Point(i / 10, i % 10));
            i++;
        }
        String[] fingers = br.readLine().split(" ");
        Point left = keyboard.get(fingers[0].charAt(0));
        Point right = keyboard.get(fingers[1].charAt(0));
        String word = br.readLine();
        int time = 0;
        Point target;
        for (int j = 0; j < word.length(); j++) {
            target = keyboard.get(word.charAt(j));
            if ((target.getY() == 4 && target.getX() != 2) || target.getY() < 4) {
                time += distance(target, left) + 1;
                left = target;
            } else {
                time += distance(target, right) + 1;
                right = target;
            }
        }
        System.out.println(time);
    }
    static int distance(Point a, Point b) {
        return (int) (Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY()));
    }
}