#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    vector<vector<int>> minPathWeights(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> minPathWeights[i][j];
        }
    }

    bool isImpossible = false;
    int answer = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            bool isEssential = true;
            for (int k = 0; k < n; k++) {
                if (i == k || j == k) continue;
                int detour = minPathWeights[i][k] + minPathWeights[k][j];
                if (minPathWeights[i][j] == detour) {
                    isEssential = false;
                } else if (minPathWeights[i][j] > detour) {
                    isImpossible = true;
                    break;
                }
            }

            if (isEssential) answer += minPathWeights[i][j];
            if (isImpossible) break;
        }
        if (isImpossible) break;
    }

    if (isImpossible) {
        cout << -1;
    } else cout << answer;

    return 0;
}