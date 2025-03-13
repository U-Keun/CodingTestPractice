#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n, m, k; cin >> n >> m >> k;
    queue<int> babies;
    for (int i = 0; i < m; i++) {
        int val; cin >> val;
        babies.push(val);
    }

    vector<int> record;
    while (k--) {
        record.assign(n, 0);
        while (!babies.empty()) {
            int tmp = babies.front(); babies.pop();
            
            int left = ((tmp - 1) % n + n) % n, 
                right = (tmp + 1) % n;
            record[left]++;
            record[right]++;
        }

        for (int i = 0; i < n; i++) {
            if (record[i] != 1) continue;
            babies.push(i);
        }
    }

    cout << babies.size() << '\n';

    return EXIT_SUCCESS;
}