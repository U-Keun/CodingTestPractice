#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n, m; cin >> n >> m;

    vector<int> prefix_sum(n + 1);
    int num;
    for (int i = 1; i <= n; i++) {
        cin >> num;
        prefix_sum[i] = prefix_sum[i - 1] + num;
    }

    int max_sum = 0, min_prefix = 0;
    for (int i = m; i <= n; i++) {
        min_prefix = min(min_prefix, prefix_sum[i - m]);
        max_sum = max(max_sum, prefix_sum[i] - min_prefix);
    }

    cout << max_sum << '\n';

    return EXIT_SUCCESS;
}