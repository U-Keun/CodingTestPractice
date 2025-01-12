#include <iostream>
#include <vector>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> parents;

int find(int x) {
    if (parents[x] == x) return x;
    return parents[x] = find(parents[x]);
}

bool union_nodes(int x, int y) {
    x = find(x);
    y = find(y);
    if (x == y) return false;
    else if (x < y) parents[y] = x;
    else if (x > y) parents[x] = y;
    return true;
}

int main() {
    FAST_IO

    int n, q; cin >> n >> q;
    parents.resize(n + 1);
    REP(i, 1, n) parents[i] = i;

    vector<int> reserved(n + 1);
    REP(i, 2, n) cin >> reserved[i];

    vector<vector<int>> queries(n + q - 1, vector<int>(3));
    REP(i, 0, n + q - 2) {
        cin >> queries[i][0];
        if (queries[i][0] == 0) cin >> queries[i][1];
        else cin >> queries[i][1] >> queries[i][2];
    }

    stack<bool> answer;
    int idx = n + q - 2;
    while (idx >= 0) {
        if (queries[idx][0] == 0) {
            int node = queries[idx][1];
            union_nodes(node, reserved[node]);
        } else {
            int u = queries[idx][1], v = queries[idx][2];
            answer.push(find(u) == find(v));
        }
        idx--;
    }

    while (!answer.empty()) {
        if (answer.top()) cout << "YES\n";
        else cout << "NO\n";
        answer.pop();
    }

    return EXIT_SUCCESS;
}