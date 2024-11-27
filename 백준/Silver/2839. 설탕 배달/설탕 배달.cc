#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n, answer = -1;
    cin >> n;

    for (int i = n / 5; i >= 0; i--) {
        if ((n - i * 5) % 3 == 0) {
            answer = i + (n - i * 5) / 3;
            break;
        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}