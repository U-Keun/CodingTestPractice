#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;
    for (int i = n; i > 0; i--) {
        for (int j = 0; j < i; j++) {
            cout << '*';
        }
        cout << '\n';
    }

    return 0;
}