#include <string>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<int> parents;

int find(int x) {
    if (x == parents[x]) return x;
    return parents[x] = find(parents[x]);
}

bool unionTree(int x, int y) {
    x = find(x);
    y = find(y);
    
    if (x == y) return false;
    
    if (x < y) {
        parents[y] = x;
    } else parents[x] = y;
    
    return true;
}

int solution(int n, vector<vector<int>> costs) {
    REP(i, 0, n - 1) parents.push_back(i);
    
    priority_queue<tuple<int, int, int>,
    			vector<tuple<int, int, int>>,
    			greater<>> pq;
    
    for (const auto& edge : costs) {
        pq.emplace(edge[2], edge[0], edge[1]);
    }
    
    int count = 0, answer = 0;
    while (!pq.empty() && count < n - 1) {
        int wt = get<0>(pq.top()),
        		u = get<1>(pq.top()),
        		v = get<2>(pq.top());
        pq.pop();
        
        if (unionTree(u, v)) {
            answer += wt;
        }
    }
    
    return answer;
}