#include <string>
#include <vector>
#include <algorithm>
#include <functional>

using namespace std;

int solution(vector<int> A, vector<int> B) {
    sort(A.begin(), A.end(), greater<int>());
    sort(B.begin(), B.end(), greater<int>());
    
    int answer = 0, l = A.size(), idxA = 0, idxB = 0;
    while (idxA < l && idxB < l) {
        if (A[idxA] < B[idxB]) {
            answer++;
            idxB++; 
        }
        idxA++;
    }
    
    return answer;
}