#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int k;
    cin >> k;

    vector<bool> mark(7850000, false);
    vector<int> prime_numbers;
    for (int i = 2; i <= 7850000; ++i) {
        if (mark[i]) continue;
        prime_numbers.push_back(i);
        if (prime_numbers.size() >= k) break;
        for (long long j = 1LL * i * i; j <= 7850000; j += i) {
            if (j > 7850000 || mark[j]) continue;
            mark[j] = true;
        }
    }

    cout << prime_numbers[k - 1];

    return 0;
}