#include <iostream>
#include <unordered_map>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n; cin >> n;
    unordered_map<int, int> drugs;
    int sym, drug;
    while(n-- > 0) {
        cin >> sym >> drug;
        drugs[sym] = drug;
    }

    int m; cin >> m;
    while (m-- > 0) {
        int num, s; cin >> num;
        bool hasDied = false;
        vector<int> ret;
        while (num-- > 0) {
            cin >> s;
            if (drugs.find(s) == drugs.end()) {
                hasDied = true;
                continue;
            }
            ret.emplace_back(drugs[s]);
        }

        if (hasDied) cout << "YOU DIED\n";
        else {
            for (int i : ret) {
                cout << i << ' ';
            }
            cout << '\n';
        }
    }

    return EXIT_SUCCESS;
}