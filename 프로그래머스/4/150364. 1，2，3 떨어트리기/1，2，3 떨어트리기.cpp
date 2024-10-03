#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <stack>
#include <unordered_map>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int findLeaf(int current, vector<queue<int>>& graph) {
    if (graph[current].empty()) return current;
    
    int next = graph[current].front();
    graph[current].push(next);
    graph[current].pop();
    
    return findLeaf(next, graph);
}

bool isEnough(int dropped, int target) {
    return dropped <= target && dropped * 3 >= target;
}

void fillStack(int dropped, int target, stack<int>& s) {
    REP(i, 1, dropped) {
        int next = min(3, target - (dropped - i));
        s.push(next);
        target -= next;
    }
}

vector<int> solution(vector<vector<int>> edges, vector<int> target) {
    sort(edges.begin(), edges.end()); 
    int n = target.size();
    
    vector<queue<int>> graph(n);
    for (const auto& edge : edges) {
        graph[edge[0] - 1].push(edge[1] - 1);
    }
    
    int stop_flag = 0;
    for (int num : target) {
        if (num > 0) stop_flag++;
    }
    
    vector<int> dropped(n), seq;
    int cur, total_dropped = 0;
    while (stop_flag > 0) {
        cur = findLeaf(0, graph);
        
        // 반복문 종료 결정을 위한 로직
        if (!isEnough(dropped[cur], target[cur]) && isEnough(dropped[cur] + 1, target[cur])) {
            stop_flag--;
        }
        
        dropped[cur]++;
        seq.push_back(cur);
        
        if (dropped[cur] > target[cur]) { // 너무 많이 떨어졌다면
            return { -1 };
        }
    }
    
    unordered_map<int, stack<int>> combinations;
    REP(i, 0, n - 1) {
        if (dropped[i] == 0) continue;
        fillStack(dropped[i], target[i], combinations[i]);
    }
    
    vector<int> answer;
    for (int idx : seq) {
        answer.push_back(combinations[idx].top());
        combinations[idx].pop();
    }  
    
    return answer;
}