#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    long long n;
    cin >> n;

    if (n % 7 == 2 || n % 7 == 0) cout << "CY\n";
    else cout << "SK\n";

    return 0;
}