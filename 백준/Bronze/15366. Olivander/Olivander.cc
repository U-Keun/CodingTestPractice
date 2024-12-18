#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n; cin >> n;
    vector<int> wands(n), boxes(n);
    REP(i, 0, n - 1) cin >> wands[i];
    REP(i, 0, n - 1) cin >> boxes[i];
    sort(wands.begin(), wands.end());
    sort(boxes.begin(), boxes.end());

    bool possible = true;
    REP(i, 0, n - 1) {
        if (wands[i] > boxes[i]) {
            possible = false;
            break;
        }
    }

    if (possible) cout << "DA\n";
    else cout << "NE\n";

    return EXIT_SUCCESS;
}