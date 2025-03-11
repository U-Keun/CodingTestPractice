#include <iostream>

using namespace std;

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

int main() {
    FAST_IO;
    int n; cin >> n;

    n--;
    long long answer = n;
    while (n > 1) {
        answer *= n - 2;
        answer %= 1000000007LL;
        n -= 2;
    }

    cout << answer << '\n';
    return EXIT_SUCCESS;
}