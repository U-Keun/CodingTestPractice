#include <string>
#include <vector>
#include <queue>

using namespace std;

long long solution(int n, vector<int> works) {
    priority_queue<int> q;
    for (int work : works) {
        q.push(work);
    }
    
    while (n-- > 0 && !q.empty()) {
        int tmp = q.top();
        q.pop();
        tmp--;
        if (tmp > 0) q.push(tmp);
    }
    long long answer = 0;
    while (!q.empty()) {
        answer += q.top() * q.top();
        q.pop();
    }
    return answer;
}