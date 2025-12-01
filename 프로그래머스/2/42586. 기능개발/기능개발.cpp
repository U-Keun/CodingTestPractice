#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> indices;
    int n = progresses.size();
    for (int i = 0; i < n; i++) indices.push(i);
    int time = (100 - progresses[0]) / speeds[0] + ((100 - progresses[0]) % speeds[0] ? 1 : 0), cnt;
    while (!indices.empty()) {
        cnt = 0;
        while (!indices.empty() && progresses[indices.front()] + speeds[indices.front()] * time >= 100) {
            cnt++;
            indices.pop();
        }
        answer.push_back(cnt);
        while (!indices.empty() && progresses[indices.front()] + speeds[indices.front()] * time < 100) time++;
    }
    
    return answer;
}