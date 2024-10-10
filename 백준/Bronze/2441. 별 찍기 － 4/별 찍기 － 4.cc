#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) cout << ' ';
        for (int j = i; j < n; j++) cout << '*';
        cout << '\n';
    }

    return 0;
}