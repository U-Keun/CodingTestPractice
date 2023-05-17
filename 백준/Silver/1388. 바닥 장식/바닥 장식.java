import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        String[][] graph = new String[n][m];
        int count = 0;

        String[] row;
        for (int i = 0; i < n; i++) {
            row = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                graph[i][j] = row[j];
                if (i == 0) {
                    if (j == 0) count++;
                    else {
                        if (row[j].equals("-") && !row[j].equals(row[j - 1])) count++;
                        else if (row[j].equals("|")) count++;
                    }
                } else {
                    if (j == 0) {
                        if (row[j].equals("|") && !row[j].equals(graph[i - 1][j])) count++;
                        else if (row[j].equals("-")) count++;
                    } else {
                        if (row[j].equals("-") && !row[j].equals(graph[i][j - 1])) count++;
                        else if (row[j].equals("|") && !row[j].equals(graph[i - 1][j])) count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}