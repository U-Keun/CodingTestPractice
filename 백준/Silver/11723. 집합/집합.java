import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Set {
    public ArrayList<Integer> set = new ArrayList<>();
    public void add(int x) {
        if (!set.contains(x)) set.add(x);
    }
    public void remove(int x) {
        if (set.contains(x)) set.remove(set.indexOf(x));
    }
    public int check(int x) {
        if (set.contains(x)) return 1;
        else return 0;
    }
    public void toggle(int x) {
        if (set.contains(x)) set.remove(set.indexOf(x));
        else set.add(x);
    }
    public void all() {
        set.clear();
        for (int i = 1; i <= 20; i++) {
            set.add(i);
        }
    }
    public void empty() {
        this.set.clear();
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Set mySet = new Set();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add":
                    mySet.add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    mySet.remove(Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    print.append(mySet.check(Integer.parseInt(st.nextToken()))).append('\n');
                    break;
                case "toggle":
                    mySet.toggle(Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    mySet.all();
                    break;
                case "empty":
                    mySet.empty();
                    break;
            }
        }
        System.out.println(print);
    }
}