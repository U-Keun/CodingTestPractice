import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Word> queue = new PriorityQueue<>();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            queue.add(new Word(br.readLine()));
        }
        for (int i = 0; i < N; i++) {
            print.append(queue.poll().word).append('\n');
        }
        System.out.println(print);
    }
}
class Word implements Comparable<Word> {
    String word;
    ArrayList<Object> splitWord;
    public Word(String word) {
        this.word = word;
        this.splitWord = new ArrayList<>();
        String tmp = "";
        for (char c:word.toCharArray()) {
            if ('0' <= c && c <= '9') {
                tmp += c;
            } else {
                if (!tmp.isEmpty()) {
                    this.splitWord.add(tmp);
                    tmp = "";
                }
                this.splitWord.add(c);
            }
        }
        if (!tmp.isEmpty()) this.splitWord.add(tmp);
    }
    @Override
    public int compareTo(Word otherWord) {
        int l = Math.min(this.splitWord.size(), otherWord.splitWord.size());
        for (int i = 0; i < l; i++) {
            Object o1 = this.splitWord.get(i);
            Object o2 = otherWord.splitWord.get(i);
            if (o1 instanceof String) {
                if (o2 instanceof String) {
                    BigInteger n1 = new BigInteger((String) o1);
                    BigInteger n2 = new BigInteger((String) o2);
                    if (n1.equals(n2)) {
                        if (((String) o1).length() == ((String) o2).length()) continue;
                        return ((String) o1).length() - ((String) o2).length();
                    }
                    return n1.compareTo(n2);
                } else {
                    return -1;
                }
            } else {
                if (o2 instanceof String) {
                    return 1;
                } else {
                    Character c1 = (Character) o1;
                    Character c2 = (Character) o2;
                    if (c1 == c2) continue;
                    if (Character.toUpperCase(c1) == Character.toUpperCase(c2)) {
                        return c1 - c2;
                    } else return Character.toUpperCase(c1) - Character.toUpperCase(c2);
                }
            }
        }
        if (this.splitWord.size() - l == 0) return -1;
        else return 1;
    }
}