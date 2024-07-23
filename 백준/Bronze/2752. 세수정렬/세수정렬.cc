#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int a, b, c;

    cin >> a >> b >> c;

    int *first, *second, *third;

    first = (a > b) ? &a : &b;
    second = (a > b) ? &b : &a;

    if (c > *first) {
        third = second;
        second = first;
        first = &c;
    } else if (c > *second) {
        third = second;
        second = &c;
    } else {
        third = &c;
    }

    cout << *third << " " << *second << " " << *first << '\n';

    return 0;
}