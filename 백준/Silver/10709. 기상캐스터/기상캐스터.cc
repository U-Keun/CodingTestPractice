#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int h, w;
    cin >> h >> w;

    char info;
    vector<vector<int>> answer(h, vector<int>(w, -1));
    REP(i, 0, h - 1) {
        int count = -1;
        REP(j, 0, w - 1) {
            cin >> info;
            if (info == 'c') {
                count = 0;
                answer[i][j] = count;
            } else if (count >= 0) {
                answer[i][j] = ++count;
            }
        }
    }

    REP(i, 0, h - 1) {
        REP(j, 0, w - 1) {
            cout << answer[i][j] << ' ';
        }
        cout << '\n';
    }

    return 0;
}