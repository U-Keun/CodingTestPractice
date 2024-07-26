#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(pair<int, int> a, pair<int, int> b) {
    if (a.second == b.second) {
        return a.first < b.first;
    }
    return a.second > b.second;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;

    int t, s;

    vector<pair<int, int>> tasks;
    for (int i = 0; i < n; i++) {
        cin >> t >> s;
        tasks.emplace_back(make_pair(t, s));
    }

    sort(tasks.begin(), tasks.end(), compare);

    int current = tasks[0].second;
    for (int i = 0; i < n; ++i) {
        current = min(current, tasks[i].second);
        current -= tasks[i].first;
    }

    if (current < 0) cout << -1 << '\n';
    else cout << current << '\n';
    return 0;
}