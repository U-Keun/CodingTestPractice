#include <iostream>

using namespace std;

int main() {
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);

    int a, b, c;

    cin >> a >> b >> c;

    int first = a, second;
    if (a > b) {
        second = b;
    } else {
        first = b;
        second = a;
    }

    if (c > first) {
        cout << first <<'\n';
        return 0;
    } else {
        if (second > c) {
            cout << second << '\n';
            return 0;
        }
        cout << c << '\n';
    }

    return 0;
}