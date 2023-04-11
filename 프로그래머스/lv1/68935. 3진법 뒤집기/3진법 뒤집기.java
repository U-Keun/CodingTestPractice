class Solution {
    public int solution(int n) {
        int answer = 0;
        int k = 0;
        while (n>=Math.pow(3,k)) {
            k++;
        }
        int[] tri = new int[k];

        for (int i=0; i<k; i++) {
            tri[i] = n / (int)Math.pow(3, k-1-i);
            n = n % (int)Math.pow(3,k-1-i);
        }
        
        int[] irt = new int[tri.length];
        
        for (int i = tri.length-1; i>=0; i--) {
            irt[i] = tri[tri.length - i - 1];
        }
        
        for (int i = 0; i < k; i++) {
            answer += irt[i] * (int)Math.pow(3,k-1-i);
        }

        return answer;
    }
}