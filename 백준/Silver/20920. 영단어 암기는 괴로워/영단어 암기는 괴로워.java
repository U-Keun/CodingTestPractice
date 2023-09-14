import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> count = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) continue;
            count.putIfAbsent(word, 0);
            count.replace(word, count.get(word) + 1);
        }
        br.close();
        PriorityQueue<EnglishWord> note = new PriorityQueue<>();
        for (Map.Entry<String, Integer> e:count.entrySet()) {
            note.add(new EnglishWord(e.getKey(), e.getValue()));
        }
        StringBuilder print = new StringBuilder();
        while (!note.isEmpty()) print.append(note.poll().word).append('\n');
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
}
class EnglishWord implements Comparable<EnglishWord> {
    String word;
    int frequency;
    public EnglishWord(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }
    public int compareTo(EnglishWord theOther) {
        if (this.frequency == theOther.frequency) {
            if (this.word.length() == theOther.word.length()) {
                return this.word.compareTo(theOther.word);
            }
            return theOther.word.length() - this.word.length();
        }
        return theOther.frequency - this.frequency;
    }
}