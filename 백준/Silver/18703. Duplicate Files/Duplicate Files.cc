#include <iostream>
#include <unordered_map>
#include <string>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int t, n; cin >> t;
    while (t--) {
        cin >> n;
        unordered_map<string, int> record;

        string name;
        int id;
        while (n--) {
            cin >> name >> id;
            if (record[name] == 0 || id < record[name]) record[name] = id;
        }

        vector<int> indices;
        for (const auto &tmp : record) {
            indices.push_back(tmp.second);
        }

        sort(indices.begin(), indices.end());
        for (int num : indices) cout << num << ' ';
        cout << '\n';
    }

    return EXIT_SUCCESS;
}