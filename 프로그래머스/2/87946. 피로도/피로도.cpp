#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> cur;
vector<bool> used;

void dfs(int& answer, int& k, vector<vector<int>>& dungeons, int d, int n) {
    if (d == n) {
        int tmp = k, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (tmp < cur[i][0]) continue;
            tmp -= cur[i][1];
            cnt++;
        }
        answer = max(answer, cnt);
        return;
    }
    for (int i = 0; i < n; i++) {
        if (used[i]) continue;
        used[i] = true;
        cur.push_back(dungeons[i]);
        dfs(answer, k, dungeons, d + 1, n);
        cur.pop_back();
        used[i] = false;
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    int answer = -1, n = dungeons.size();
    used.assign(n, false);
    dfs(answer, k, dungeons, 0, n);
    return answer;
}