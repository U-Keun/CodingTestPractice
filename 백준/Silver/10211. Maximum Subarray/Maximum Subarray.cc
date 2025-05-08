#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int t; cin >> t;
    while (t--) {
        int n; cin >> n;
        vector<int> arr(n);
        for (int i = 0; i < n; i++) cin >> arr[i];

        int max_sum = -1000000;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += arr[j];
                max_sum = max(max_sum, s);
            }
        }

        cout << max_sum << '\n';
    }

    return EXIT_SUCCESS;
}