import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input1, input2;
        StringBuilder print = new StringBuilder();

        List<Character> charList1 = new LinkedList<>();
        List<Character> charList2 = new LinkedList<>();

        while (sc.hasNextLine()) {
            input1 = sc.nextLine();
            input2 = sc.nextLine();

            for (char c:input1.toCharArray()) {
                charList1.add(c);
            }
            for (char c:input2.toCharArray()) {
                if (charList1.contains(c)) {
                    charList2.add(c);
                    charList1.remove(charList1.indexOf(c));
                }
            }
            Collections.sort(charList2);

            if (charList2.isEmpty()) print.append(" ");
            else {
                for (char c:charList2) {
                    print.append(c);
                }
            }
            print.append('\n');
            charList1.clear();
            charList2.clear();
        }
        System.out.println(print);
    }
}