#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, t;
    cin >> n;
    vector<int> billboard(n);
    REP(i, 0, n - 1) cin >> billboard[i];
    cin >> t;

    sort(billboard.begin(), billboard.end());

    long long cur = t * (billboard[0] / t + 1);
    int idx = 0, count = 0;
    while (idx < n) {
        while (idx < n && billboard[idx] < cur) idx++;
        count++;
        while (billboard[idx] > cur) cur += t;
    }

    cout << count;

    return 0;
}