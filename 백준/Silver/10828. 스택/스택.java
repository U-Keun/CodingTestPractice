import java.util.Scanner;
import java.util.Stack;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        int a;
        String b;
        Scanner scanner = new Scanner(System.in);
        StringBuffer print = new StringBuffer();
        a = parseInt(scanner.nextLine());
        Stack<String> list = new Stack<>();
        for (int i = 0; i < a; i++){
            int answer;
            b = scanner.nextLine();
            String[] order = b.split(" ");
            if (order[0].equals("push")) {
                list.push(order[1]);
            } else if (order[0].equals("pop")) {
                if (list.isEmpty()) print.append(-1).append('\n');
                else {
                    print.append(list.peek()).append('\n');
                    list.pop();
                }
            } else if (order[0].equals("size")) {
                print.append(list.size()).append('\n');
            } else if (order[0].equals("empty")) {
                if (list.isEmpty()) print.append(1).append('\n');
                else print.append(0).append('\n');
            } else {
                if (list.isEmpty()) print.append(-1).append('\n');
                else print.append(list.peek()).append('\n');
            }

        }
        System.out.println(print);
    }
}