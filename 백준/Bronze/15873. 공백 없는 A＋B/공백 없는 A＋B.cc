#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int input;
    cin >> input;
    if (input == 1010) {
        cout << 20;
    } else if (input > 100) {
        if (input % 10 == 0) cout << (input / 100 + 10);
        else cout << (input / 10 + input % 10);
    } else {
        cout << (input / 10 + input % 10);
    }

    return 0;
}