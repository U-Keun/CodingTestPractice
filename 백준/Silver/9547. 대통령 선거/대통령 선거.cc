#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int t; cin >> t;
    vector<vector<int>> pref;
    vector<int> record;
    while (t--) {
        int c, v; cin >> c >> v;
        pref.assign(v, vector<int>(c));
        record.assign(c + 1, 0);
        
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < c; j++) {
                cin >> pref[i][j];
            }
            record[pref[i][0]]++;
        }

        bool determined = false;
        pair<int, int> candi = { 0, 0 };
        for (int i = 1; i <= c; i++) {
            if (record[i] > v / 2) {
                determined = true;
            }
            if (record[i] > record[candi.first]) {
                candi.second = candi.first;
                candi.first = i;
            } else if (record[i] > record[candi.second]) {
                candi.second = i;
            }
        }

        if (determined) {
            cout << candi.first << " 1\n";
            continue;
        }

        int cnt_f = 0, cnt_s = 0;
        for (int i = 0; i < v; i++) {
            int idx = 0;
            while (idx < c) {
                if (pref[i][idx] == candi.first) {
                    cnt_f++;
                    break;
                } else if (pref[i][idx] == candi.second) {
                    cnt_s++;
                    break;
                }
                idx++;
            }
        }

        if (cnt_f > cnt_s) cout << candi.first;
        else cout << candi.second;
        cout << " 2\n";
    }

    return EXIT_SUCCESS;
}