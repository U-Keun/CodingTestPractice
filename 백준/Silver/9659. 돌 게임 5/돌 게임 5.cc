#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    long long n;
    cin >> n;

    if (n % 2 == 1) cout << "SK\n";
    else cout << "CY\n";

    return 0;
}