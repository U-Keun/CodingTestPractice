import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int K, answer = 0;
    static int[] tmp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        tmp = new int[N];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        mergeSort(A, 0, N - 1);
        if (answer == 0) bw.write(String.valueOf(-1));
        else bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
    static void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }
    static void merge(int[] A, int p, int q, int r) {
        int i = p, j = q + 1, t = p;
        while (i <= q && j <= r) {
            if (A[i] <= A[j]) {
                tmp[t++] = A[i++];
            } else tmp[t++] = A[j++];
        }
        while (i <= q) tmp[t++] = A[i++];
        while (j <= r) tmp[t++] = A[j++];
        i = p;
        while (i <= r) {
            if (--K == 0) answer = tmp[i];
            A[i] = tmp[i++];
        }
    }
}