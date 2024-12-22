#include <iostream>
#include <unordered_map>
#include <queue>
#include <functional>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long

int main() {
    FAST_IO

    ll n, num; cin >> n;
    unordered_map<ll, int> numbers;
    while (n-- > 0) {
        cin >> num;
        numbers[num]++;
    }

    auto cmp = [](pair<int, ll> a, pair<int, ll> b) {
        if (a.first == b.first) return a.second > b.second;
        return a.first < b.first;
    };
    priority_queue<pair<int, ll>, vector<pair<int, ll>>, decltype(cmp)> pq(cmp);
    for (auto e : numbers) {
        pq.emplace(e.second, e.first);
    }

    cout << pq.top().second;

    return EXIT_SUCCESS;
}