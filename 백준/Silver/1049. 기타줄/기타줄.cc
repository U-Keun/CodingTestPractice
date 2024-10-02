#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);
#define REP(i,a,b) for (int i = a; i <= b; i++)

using namespace std;

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<int> pack(m), one(m);
    REP(i, 0, m - 1) {
        cin >> pack[i] >> one[i];
    }

    sort(pack.begin(), pack.end());
    sort(one.begin(), one.end());

    int packs = n / 6;

    int answer = packs * ((6 * one[0] > pack[0]) ? pack[0] : 6 * one[0]);

    int res = n % 6;
    if (res != 0) {
        answer += (res * one[0] > pack[0]) ? pack[0] : res * one[0];
    }

    cout << answer;
    return 0;
}