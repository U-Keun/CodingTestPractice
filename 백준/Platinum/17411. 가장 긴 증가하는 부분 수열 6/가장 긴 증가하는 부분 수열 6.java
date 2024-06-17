import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    private static int start;
    private static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        PriorityQueue<Pair> pairs = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pairs.add(new Pair(i, numbers[i]));
        }

        start = 1;
        while (start < N) start *= 2;

        tree = new int[start * 2 + 1][2];
        while (!pairs.isEmpty()) {
            Pair tmp = pairs.poll();
            int[] length = getMax(0, tmp.index);
            if (length[0] == 0) length[1]++;
            length[0]++;
            updateTree(tmp.index, length);
        }

        System.out.printf("%d %d", tree[1][0], tree[1][1]);
    }

    private static class Pair implements Comparable<Pair> {
        int index, number;
        Pair(int index, int number) {
            this.index = index;
            this.number = number;
        }
        @Override
        public int compareTo(Pair other) {
            if (this.number == other.number) {
                return other.index - this.index;
            }
            return this.number - other.number;
        }
    }

    private static int[] getMax(int left, int right) {
        int[] answer = new int[2];
        left += start;
        right += start;

        while (left < right) {
            if (left % 2 == 0) left /= 2;
            else {
                if (answer[0] < tree[left][0]) {
                    answer[0] = tree[left][0];
                    answer[1] = tree[left][1];
                } else if (answer[0] == tree[left][0]) {
                    answer[1] = addMod(answer[1], tree[left][1]);
                }
                left++;
                left /= 2;
            }

            if (right % 2 == 1) right /= 2;
            else {
                if (answer[0] < tree[right][0]) {
                    answer[0] = tree[right][0];
                    answer[1] = tree[right][1];
                } else if (answer[0] == tree[right][0]) {
                    answer[1] = addMod(answer[1], tree[right][1]);
                }
                right--;
                right /= 2;
            }
        }

        if (left == right) {
            if (answer[0] < tree[left][0]) {
                answer[0] = tree[left][0];
                answer[1] = tree[left][1];
            } else if (answer[0] == tree[left][0]) {
                answer[1] = addMod(answer[1], tree[left][1]);
            }
        }

        return answer;
    }

    private static void updateTree(int index, int[] length) {
        index += start;
        tree[index][0] = length[0];
        tree[index][1] = length[1];
        index /= 2;
        while (index > 0) {
            if (tree[index * 2][0] > tree[index * 2 + 1][0]) {
                tree[index][0] = tree[index * 2][0];
                tree[index][1] = tree[index * 2][1];
            } else if (tree[index * 2][0] < tree[index * 2 + 1][0]) {
                tree[index][0] = tree[index * 2 + 1][0];
                tree[index][1] = tree[index * 2 + 1][1];
            } else {
                tree[index][0] = tree[index * 2][0];
                tree[index][1] = addMod(tree[index * 2][1], tree[index * 2 + 1][1]);
            }
            index /= 2;
        }
    }

    private static int addMod(int a, int b) {
        long value = (long) a + b;
        value %= 1000000007;
        return (int) value;
    }
}