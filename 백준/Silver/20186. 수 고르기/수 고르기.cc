#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n, k; cin >> n >> k;
    vector<int> numbers(n);
    for (int i = 0; i < n; i++) cin >> numbers[i];

    vector<int> indices(n);
    for (int i = 0; i < n; i++) indices[i] = i;

    sort(indices.begin(), indices.end(), [&](int i, int j) {
        return numbers[i] > numbers[j];
    });

    int answer = 0;
    for (int i = 0; i < k; i++) {
        answer += numbers[indices[i]] - i;
    }

    cout << answer << '\n';

    return EXIT_SUCCESS;
}