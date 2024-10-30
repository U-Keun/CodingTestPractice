#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int k;
    cin >> k;

    vector<bool> mark(500001, false);
    vector<int> prime_numbers;
    for (int i = 2; i <= 500000; ++i) {
        if (mark[i]) continue;
        prime_numbers.push_back(i);
        for (int j = i; j <= 500000; j += i) {
            if (mark[j]) continue;
            mark[j] = true;
        }

        if (prime_numbers.size() >= k) break;
    }

    cout << prime_numbers[k - 1];



    return 0;
}