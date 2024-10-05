#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

pair<int, int> GetCmd(int num) {
    int i = 0, j = 0, num_i = 1, num_j = 1;
    REP(k, 1, 7) {
        if (num_j * 2 > num) break;
        num_j *= 2;
        j++;
    }

    num -= num_j;
    REP(k, 1, 7) {
        if (num_i * 2 > num) break;
        num_i *= 2;
        i++;
    }

    if (num != num_i) return { -1, -1 };
    return { i, j };
}

int main() {
    FAST_IO

    int n;
    cin >> n;

    vector<int> keyLocker(8);
    REP(i, 0, 7) keyLocker[i] = i;
    int cmd;
    REP(i, 0, n - 1) {
        cin >> cmd;
        pair<int, int> decoded = GetCmd(cmd);
        if (decoded.first < 0) continue;

        int tmp = keyLocker[decoded.first];
        keyLocker[decoded.first] = keyLocker[decoded.second];
        keyLocker[decoded.second] = tmp;
    }

    int k;
    cin >> k;

    cout << keyLocker[k];

    return 0;
}