#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> A, vector<int> B)
{
    sort(A.rbegin(), A.rend());
    sort(B.begin(), B.end());
    int answer = 0, size = A.size();
	for (int i = 0; i < size; i++) {
        answer += A[i] * B[i];
    }

    return answer;
}