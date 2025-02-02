#include <iostream>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO
    int n; cin >> n;

    long long num;
    while (n--) {
        cin >> num;
        long long root = sqrt(num);
        if (root * root == num) cout << 1;
        else cout << 0;
        cout << ' ';
    }

    return EXIT_SUCCESS;
}