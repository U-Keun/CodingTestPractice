#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int w, n, p, h, answer = 0;
    cin >> w >> n >> p;
    while (p-- > 0) {
        cin >> h;
        if (h < w || h > n) continue;
        answer++;
    }

    cout << answer;

    return 0;
}