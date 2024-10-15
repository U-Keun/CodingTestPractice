#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    cin >> n;

    vector<int> answer(n);
    int inv;
    REP(i, 1, n) {
        cin >> inv;
        int cnt = 0, idx = 0;
        while (inv > cnt) {
            if (answer[idx] == 0) {
                cnt++;
                idx++;
            }
            while (answer[idx] != 0) idx++;
        }

        while (answer[idx] != 0) idx++;
        answer[idx] = i;
    }

    REP(i, 0, n - 1) cout << answer[i] << ' ';

    return 0;
}