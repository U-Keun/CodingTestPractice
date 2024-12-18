#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, m; cin >> n >> m;
    while (n != 0 && m != 0) {
        vector<int> tmp(n);
        REP(i, 0, n - 1) cin >> tmp[i];
        int answer = -1;
        REP(i, 0, n - 2) {
            REP(j, i + 1, n - 1) {
                int val = tmp[i] + tmp[j];
                if (val <= m) answer = max(answer, val);
            }
        }

        if (answer == -1) cout << "NONE\n";
        else cout << answer << '\n';
        cin >> n >> m;
    }

    return EXIT_SUCCESS;
}