#include <iostream>
#include <string>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> cards;
int n, k;

void deal() {
    vector<int> tmp(n);
    REP(i, 0, n - 1) {
        int r = i / 3, c = i % 3;
        tmp[c * k + r] = cards[i];
    }
    cards.swap(tmp);
}

int main() {
    FAST_IO

    int d; cin >> n >> d;
    k = n / 3;
    cards.resize(n);
    REP(i, 0, n - 1) {
        cards[i] = i + 1;
    }

    string input;
    vector<int> record(n);
    REP(i, 0, d - 1) {
        cin >> input;
        int c;
        if (input == "first") c = 0;
        else if (input == "second") c = 1;
        else c = 2;

        REP(j, 0, k - 1) {
            record[cards[j * 3 + c] - 1]++;
        }

        deal();
    }

    REP(i, 0, n - 1) {
        if (record[i] < d) continue;
        cout << (i + 1) << ' ';
    }

    return EXIT_SUCCESS;
}