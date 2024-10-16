#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, record, p;
    cin >> n >> record >> p;
    vector<int> rating(p, -1);
    REP(i, 0, n - 1) {
        cin >> rating[i];
    }

    sort(rating.begin(), rating.end(), greater<>());

    if (rating[p - 1] >= record) {
        cout << -1;
        return 0;
    }

    REP(i, 0, p - 1) {
        if (rating[i] > record) continue;

        if (i != p - 1) cout << (i + 1);
        else {
            if (rating[i] < record) cout << (i + 1);
            else cout << -1;
        }
        break;
    }

    return 0;
}