#include <iostream>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    cin >> n;
    int ti;
    while (n-- > 0) {
        cin >> ti;
        unordered_map<long long, int> record;
        long long num;
        REP(i, 0, ti - 1) {
            cin >> num;
            record[num]++;
        }

        long long idx;
        int max = 0;
        for (auto& pair : record) {
            if (max < pair.second) {
                idx = pair.first;
                max = pair.second;
            }
        }

        if (max > ti / 2) cout << idx;
        else cout << "SYJKGW";
        cout << '\n';
    }

    return 0;
}