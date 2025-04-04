#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int t; cin >> t;
    while (t--) {
        int n, e; cin >> n >> e;
        
        vector<int> line(n);
        for (int i = 0; i < n; i++) {
            line[i] = i + 1;
        }

        int p;
        while (e--) {
            cin >> p;
            auto it = find(line.begin(), line.end(), p);
            
            if (it != line.end()) {
                line.erase(it);
                line.insert(line.begin(), p);
            }
        }

        cout << line[n - 1] << '\n';
    }

    return EXIT_SUCCESS;
}