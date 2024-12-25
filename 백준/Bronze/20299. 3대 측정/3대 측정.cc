#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int N, K, L;
    cin >> N >> K >> L;

    int count = 0, a, b, c;
    vector<int> ratings;
    while (N-- > 0) {
        cin >> a >> b >> c;
        if (a < L || b < L || c < L) continue;
        if (a + b + c < K) continue;

        count++;
        ratings.emplace_back(a);
        ratings.emplace_back(b);
        ratings.emplace_back(c);
    }

    cout << count << '\n';
    for (int rating  :ratings) cout << rating << ' ';

    return EXIT_SUCCESS;
}