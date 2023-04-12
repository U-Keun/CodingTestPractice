class Solution {
    public int solution(int[] array) {
        int[] freq = new int[1000]; 
        for(int i : array){ // 
            freq[i] += 1; // freq : i 번째 자리에 i가 몇 번 나오는지 기록함
        }
        boolean dup = false; // 중복 찾기위해 설정
        int max = 0; 
        int k = 0;
        for(int j = 0; j < 1000; j++){ // freq를 탐색하면서 가장 많이 나온 수가 무엇인지 찾을 거임
            if(max <= freq[j]){ // 일단 크거나 같은 경우 찾고
                if(max == freq[j]) dup = true; // 같으면 중복을 true로
                else {
                    dup = false;
                    max = freq[j];
                    k = j;   // 다르면(크다는 거니까) 중복을 다시 false로 바꾸고 k에 그 숫자를 기록
                }
        	}
        }
        if(dup) return -1;
        else return k;
	}	
}