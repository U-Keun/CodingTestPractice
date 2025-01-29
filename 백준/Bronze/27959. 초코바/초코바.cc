#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n, m; cin >> n >> m;
    if (100 * n >= m) cout << "Yes\n";
    else cout << "No\n";

    return EXIT_SUCCESS;
}