import java.util.Arrays;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[] union = new int[n];
        for (int i=0; i<n;i++) {
            union[i] = arr1[i] | arr2[i];
        }
        for (int i = 0; i < n; i++) {
            answer[i] = convert(n, union[i]);
        }
        return answer;
    }
    public String convert(int n, int k) {
        String hashes = "";
        String bin = Integer.toBinaryString(k);
        String[] binSplit = bin.split("");
        if (bin.length() != n) {
            for (int j = 0; j < n - bin.length(); j++) hashes += " ";
        }
        for (String str : binSplit) {
            if (str.equals("1")) hashes += "#";
            else hashes += " ";
        }
        return hashes;
    }
}