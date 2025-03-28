#include <iostream>
#include <vector>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n; cin >> n;

    unordered_map<int, vector<int>> hor, ver;

    int x, y;
    while (n--) {
        cin >> x >> y;
        hor[x].push_back(y);
        ver[y].push_back(x);
    }

    int answer = 0;
    for (const auto &tmp : hor) {
        if (tmp.second.size() > 1) answer++;
    }
    for (const auto &tmp : ver) {
        if (tmp.second.size() > 1) answer++;
    }

    cout << answer << '\n';

    return EXIT_SUCCESS;
}