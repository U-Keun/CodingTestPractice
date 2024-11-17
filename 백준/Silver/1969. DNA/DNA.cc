#include <iostream>
#include <unordered_map>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    unordered_map<char, char> index = {
            {'A', '0' },
            {'C', '1' },
            {'G', '2' },
            {'T', '3' },
            { '0', 'A' },
            { '1', 'C' },
            { '2', 'G' },
            { '3', 'T' }
    };

    int n, m;
    cin >> n >> m;

    vector<vector<int>> record(m, vector<int>(4, 0));
    string input;
    REP(i, 0, n - 1) {
        cin >> input;
        REP(j, 0, m - 1) {
            record[j][index[input[j]] - '0']++;
        }
    }

    string dna = "";
    int idx, cur = 0, sum = 0;
    REP(i, 0, m - 1) {
        idx = 0;
        REP(j, 1, 3) {
            if (record[i][idx] < record[i][j]) idx = j;
        }

        dna += index['0' + idx];
        sum += n - record[i][idx];
    }

    cout << dna << '\n' << sum;

    return 0;
}