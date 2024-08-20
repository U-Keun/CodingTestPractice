#include <string>
#include <vector>

using namespace std;

int solution(vector<int> cookie) {
    int l = cookie.size();
    vector<int> prefixSum(l + 1, 0);
    
    for (int i = 1; i < l + 1; i++) {
        prefixSum[i] = prefixSum[i - 1] + cookie[i - 1];
    }
    
    int answer = 0;
    
    for (int i = 1; i <= l; i++) {
        int left = i - 1, right = i + 1;

        while (left >= 0 && right < l + 1) {
            int first = prefixSum[right] - prefixSum[i], // i + 1 ~ right
            	second = prefixSum[i] - prefixSum[left]; // left + 1 ~ i
            
            if (first == second) {
                answer = max(answer, first);
                left--;
                right++;
            } else if (first < second) {
                right++;
            } else {
                left--;
            }
        }
    }
    
    return answer;
}