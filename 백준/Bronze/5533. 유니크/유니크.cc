#include <iostream>
#include <vector>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP_INC(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;
    vector<vector<int>> bet(n, vector<int>(3));
    REP_INC(i, 0, n - 1) {
        REP_INC(j, 0, 2) cin >> bet[i][j];
    }

    vector<int> score(n);
    REP_INC(i, 0, 2) {
        unordered_map<int, vector<int>> scoreCount;
        REP_INC(j, 0, n - 1) {
            scoreCount[bet[j][i]].push_back(j);
        }

        for (auto elt : scoreCount) {
            if (elt.second.size() > 1) continue;
            score[elt.second[0]] += elt.first;
        }
    }

    REP_INC(i, 0, n - 1) cout << score[i] << '\n';

    return 0;
}