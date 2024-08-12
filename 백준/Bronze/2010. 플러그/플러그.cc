#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    int consent = 1;
    while (n-- > 0) {
        consent--;
        int val;
        cin >> val;
        consent += val;
    }

    cout << consent << '\n';

    return 0;
}