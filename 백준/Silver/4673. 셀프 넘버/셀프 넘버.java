public class Main {
    public static void main(String[] args) {
        StringBuilder print = new StringBuilder();
        boolean[] visited = new boolean[10001];
        for (int i = 1; i <= 10000; i++) {
            if (!visited[i]) print.append(i).append("\n");
            int value = i, number = i;
            while (number / 10 != 0) {
                value += number % 10;
                number /= 10;
            }
            value += number;
            if (value > 10000) continue;
            visited[value] = true;
        }
        System.out.println(print);
    }
}