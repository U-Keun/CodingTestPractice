#include <iostream>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n; cin >> n;
    vector<int> plus(n), minus(n);
    for (int i = 0; i < n; i++) cin >> plus[i];
    for (int i = 0; i < n; i++) cin >> minus[i];

    double x;
    long long answer = 0;
    for (int i = 0; i < n; i++) {
        cin >> x;
        int k = llround(x * 10.0);
        if (x >= 1) {
            plus[i] = plus[i] * k / 10;
        } else {
            minus[i] = minus[i] * k / 10;
        }
        answer += plus[i] - minus[i];
    }

    cout << answer << '\n';

    return EXIT_SUCCESS;
}