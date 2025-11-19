#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n; cin >> n;
    vector<int> arr(n);

    for (int i = 0; i < n; i++) cin >> arr[i];

    int total = 0, reg = 0, q;
    for (int i = 0; i < n; i++) {
        cin >> q;
        total += arr[i];
        if (q == 0) reg += arr[i];
    }

    cout << total << '\n' << reg << '\n';

    return EXIT_SUCCESS;
}