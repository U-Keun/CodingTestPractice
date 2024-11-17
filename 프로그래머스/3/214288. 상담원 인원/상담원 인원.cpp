#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#include <tuple>
#include <iostream>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

// 멘토 수에 따른 대기 시간 계산
int calc_waitings(int mentors, vector<pair<int, int>>& counsels) {
    priority_queue<int, vector<int>, greater<int>> pq;
    int total = 0;
    
    for (auto& counsel : counsels) {
        if (pq.empty() || pq.size() < mentors) {
            if (!pq.empty() && pq.top() <= counsel.first) pq.pop();
            pq.push(counsel.first + counsel.second);
        } else {
            int end = pq.top();
            pq.pop();
            
            if (counsel.first < end) {
                total += end - counsel.first;
                pq.push(end + counsel.second);
            } else pq.push(counsel.first + counsel.second);
        }
    }
    
    return total;
}

int solution(int k, int n, vector<vector<int>> reqs) {
    vector<vector<pair<int, int>>> classify(k);
    
    // 상담 유형별 구분
    for (auto& req : reqs) {
        classify[req[2] - 1].push_back({ req[0], req[1] });
    }
    
    REP(i, 0, k - 1) sort(classify[i].begin(), classify[i].end());
    
    vector<vector<int>> waitings(k, vector<int>(n + 1, 0));
    REP(i, 0, k - 1) {
        REP(j, 1, n) {
            int waiting = calc_waitings(j, classify[i]);
            if (waiting == 0) break;
            waitings[i][j] = waiting;
        }
    }
    
    // 최상의 조합 찾기
    int answer = 0, cur = k;
    priority_queue<tuple<int, int, int>> pq;
    REP(i, 0, k - 1) {
        answer += waitings[i][1];
        if (waitings[i].size() > 1) 
            pq.push({ waitings[i][1] - waitings[i][2], i, 1 });
    }
    
    while (cur < n) {
        int diff = get<0>(pq.top()), 
        	type = get<1>(pq.top()),
        	idx = get<2>(pq.top());
        pq.pop();
        
        answer -= diff;
        if (idx < n - 1) pq.push({ waitings[type][idx + 1] - waitings[type][idx + 2], type, idx + 1 });
        cur++;
    }
    
    return answer;
}