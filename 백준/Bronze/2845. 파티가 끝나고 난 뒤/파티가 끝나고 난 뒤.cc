#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int l, p;
    cin >> l >> p;
    int t = l * p;

    int info;
    for (int i = 0; i < 5; i++) {
        cin >> info;
        cout << (info - t) << ' ';
    }


    return 0;
}