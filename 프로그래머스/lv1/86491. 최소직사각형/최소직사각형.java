class Solution {
    public int solution(int[][] size) {
        int max_h = -1;
        int max_w = -1;
        for (int i = 0; i < size.length; i++) {
            max_w = Math.max(Math.max(size[i][0],size[i][1]), max_w);
            max_h = Math.max(Math.min(size[i][0],size[i][1]),max_h);
        }

        return max_w*max_h;
    }
}