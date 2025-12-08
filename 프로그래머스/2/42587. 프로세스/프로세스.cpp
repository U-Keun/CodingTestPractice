#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int solution(vector<int> priorities, int location) {
    vector<int> sorted(priorities.begin(), priorities.end());
    sort(sorted.begin(), sorted.end(), greater<int>());
    
    queue<int> proc;
    int n = priorities.size();
    for (int i = 0; i < n; i++) proc.push(i);
    
    int answer = 1, idx = 0;
    while (!proc.empty()) {
        while (sorted[idx] > priorities[proc.front()]) {
            int tmp = proc.front();
            proc.pop(); proc.push(tmp);
        }
        if (proc.front() == location) return answer;
        proc.pop();
        idx++;
        answer++;
    }
    return -1;
}