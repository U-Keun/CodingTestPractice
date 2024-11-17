#include <iostream>
#include <unordered_map>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    char index[256];
    index['A'] = '0';
    index['C'] = '1';
    index['G'] = '2';
    index['T'] = '3';
    index['0'] = 'A';
    index['1'] = 'C';
    index['2'] = 'G';
    index['3'] = 'T';

    int n, m;
    cin >> n >> m;
    vector<int> record(m * 4, 0);
    string input;
    REP(i, 0, n - 1) {
        cin >> input;
        REP(j, 0, m - 1) {
            record[j * 4 + (index[input[j]] - '0')]++;
        }
    }

    string dna = "";
    int idx, sum = 0;
    REP(i, 0, m - 1) {
        idx = 0;
        REP(j, 1, 3) {
            if (record[i * 4 + idx] < record[i * 4 + j]) idx = j;
        }

        dna += index['0' + idx];
        sum += n - record[i * 4 + idx];
    }

    cout << dna << '\n' << sum;

    return 0;
}