#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int t, n;
    cin >> t;
    while (t-- > 0) {
        cin >> n;
        int p1 = 0, p2 = 0;
        char c1, c2;
        while (n-- > 0) {
            cin >> c1 >> c2;
            if (c1 == c2) continue;

            if ((c1 == 'R' && c2 == 'S')
            || c1 == 'S' && c2 == 'P'
            || c1 == 'P' && c2 == 'R') {
                p1++;
                continue;
            }

            p2++;
        }

        if (p1 > p2) cout << "Player 1\n";
        else if (p1 < p2) cout << "Player 2\n";
        else cout << "TIE\n";
    }

    return 0;
}