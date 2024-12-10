#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n; cin >> n;
    vector<int> answer(n, 0);
    int num;
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            cin >> num;
            answer[i] |= num;
        }
    }

    REP(i, 0, n - 1) cout << answer[i] << ' ';

    return EXIT_SUCCESS;
}