#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    vector<int> prefixSum(n + 1);
    int value;
    for (int i = 1; i <= n; i++) {
        cin >> value;
        prefixSum[i] = prefixSum[i - 1] + value;
    }

    int m;
    cin >> m;
    int a, b;
    while (m-- > 0) {
        cin >> a >> b;
        cout << prefixSum[b + 1] - prefixSum[a] << '\n';
    }

    return 0;
}