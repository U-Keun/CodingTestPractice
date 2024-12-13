#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, m; cin >> n >> m;
    char c;
    vector<int> rows(n), cols(m);
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            cin >> c;
            if (c == 'X') {
                rows[i]++;
                cols[j]++;
            }
        }
    }

    int answer = 0;
    REP(i, 0, n - 1) {
        if (!rows[i]) {
            REP(j, 0, m - 1) {
                if (!cols[j]) {
                    cols[j]++;
                    break;
                }
            }
            rows[i]++;
            answer++;
        }
    }

    REP(j, 0, m - 1) {
        if (!cols[j]) answer++;
    }

    cout << answer;

    return EXIT_SUCCESS;
}