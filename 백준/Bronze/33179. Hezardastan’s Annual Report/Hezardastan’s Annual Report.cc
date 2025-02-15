#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n, answer = 0; cin >> n;
    while (n--) {
        int input; cin >> input;
        answer += input / 2 + input % 2;
    }

    cout << answer;

    return EXIT_SUCCESS;
}