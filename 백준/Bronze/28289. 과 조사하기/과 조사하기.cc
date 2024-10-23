#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int p;
    cin >> p;

    int software = 0, embedded = 0, ai = 0, none = 0;

    int g, c, n;
    while (p-- > 0) {
        cin >> g >> c >> n;
        if (g == 1) {
            none++;
            continue;
        }

        if (c == 3) embedded++;
        else if (c == 4) ai++;
        else software++;
    }

    cout << software << '\n'
        << embedded << '\n'
        << ai << '\n'
        << none << '\n';

    return 0;
}