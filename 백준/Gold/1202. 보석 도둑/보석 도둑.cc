#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

struct Jewel {
    int weight, cost;
};

int main() {
    FAST_IO

    int N, K; cin >> N >> K;
    vector<Jewel> jewels(N);
    for (int i = 0; i < N; i++) {
        cin >> jewels[i].weight >> jewels[i].cost;
    }

    vector<int> bags(K);
    for (int i = 0; i < K; i++) {
        cin >> bags[i];
    }

    sort(jewels.begin(), jewels.end(), [](const Jewel &a, const Jewel &b) {
        return a.weight < b.weight;
    });
    sort(bags.begin(), bags.end());

    priority_queue<int> costs;
    long long total = 0;
    int idx = 0;
    for (int weight : bags) {
        while (idx < N && jewels[idx].weight <= weight) {
            costs.push(jewels[idx].cost);
            idx++;
        }
        if (!costs.empty()) {
            total += costs.top();
            costs.pop();
        }
    }

    cout << total << "\n";

    return EXIT_SUCCESS;
}