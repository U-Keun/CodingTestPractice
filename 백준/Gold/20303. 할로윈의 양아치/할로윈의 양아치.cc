#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<int> parents;

int find_parent(int x) {
    if (parents[x] == x) return x;
    return parents[x] = find_parent(parents[x]);
}

void union_tree(int x, int y) {
    x = find_parent(x);
    y = find_parent(y);

    if (x == y) return;
    if (x < y) parents[y] = x;
    else parents[x] = y;
}

int main() {
    FAST_IO

    int n, m, k;
    cin >> n >> m >> k;
    parents.resize(n);

    vector<int> candies(n);
    REP(i, 0, n - 1)  {
        parents[i] = i;
        cin >> candies[i];
    }

    int u, v;
    while (m--) {
        cin >> u >> v;
        union_tree(u - 1, v - 1);
    }

    unordered_map<int, pair<int, int>> group;
    REP(i, 0, n - 1) {
        int root = find_parent(i);
        group[root].first++;
        group[root].second += candies[i];
    }

    vector<int> dp(k);
    for (auto e : group) {
        for (int i = k - 1; i >= e.second.first; i--) {
            dp[i] = max(dp[i], e.second.second + dp[i - e.second.first]);
        }
    }

    cout << dp[k - 1];

    return EXIT_SUCCESS;
}