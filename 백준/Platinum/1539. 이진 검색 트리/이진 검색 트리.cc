#include <iostream>
#include <map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n; cin >> n;
    map<int, int> tree;
    long long answer = 0;

    int num;
    while (n--) {
        cin >> num;
        if (tree.empty()) {
            tree[num] = 1;
            answer++;
            continue;
        }

        auto it = tree.lower_bound(num);
        int depth = 0;
        if (it != tree.begin()) {
            auto left = prev(it);
            depth = left->second;
        }

        if (it != tree.end()) {
            depth = max(depth, it->second);
        }

        tree[num] = depth + 1;
        answer += depth + 1;
    }

    cout << answer;

   return EXIT_SUCCESS;
}