#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(int k, vector<int> score) {
    priority_queue<int, vector<int>, greater<int>> pq;
    vector<int> answer;
    for (int num : score) {
        pq.push(num);
        if (pq.size() > k) pq.pop();
        answer.push_back(pq.top());
    }
    return answer;
}