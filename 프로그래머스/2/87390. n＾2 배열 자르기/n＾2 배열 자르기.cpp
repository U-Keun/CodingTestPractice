#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;
    
    for (long long i = left; i <= right; i++) {
        long long row = i / n, col = i % n;
        int val = (int) (row > col ? row : col) + 1;
        answer.push_back(val);
    }
    return answer;
}