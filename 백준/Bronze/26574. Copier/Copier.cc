#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;
    int num;
    while (n-- > 0) {
        cin >> num;
        cout << num << ' ' << num << '\n';
    }

    return 0;
}