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

    int one = 1001, pack = 1001, tmpOne, tmpPack;
    REP(i, 0, m - 1) {
        cin >> tmpPack >> tmpOne;
        pack = min(pack, tmpPack);
        one = min(one, tmpOne);
    }

    int answer = (n / 6) * ((6 * one > pack) ? pack : 6 * one);

    int res = n % 6;
    if (res != 0) {
        answer += (res * one > pack) ? pack : res * one;
    }

    cout << answer;
    return 0;
}