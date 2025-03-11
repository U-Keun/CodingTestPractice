#include <iostream>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)
#define REP(i, a, b) for (int i = a; i <= b; i++)

using namespace std;

string custom_order;
unordered_map<char, int> priority;

bool custom_compare(const string &a, const string &b) {
    int a_len = a.size(), b_len = b.size();
    for (int i = 0; i < min(a_len, b_len); i++) {
        if (a[i] != b[i]) return priority[a[i]] < priority[b[i]];
    }
    return a_len < b_len;
}

int main() {
    FAST_IO;

    int n, year = 1; cin >> n;
    while (n) {
        cin >> custom_order;
        int l = custom_order.size();
        REP(i, 0, l - 1) {
            priority[custom_order[i]] = i;
        }
        
        vector<string> words(n);
        REP(i, 0, n - 1) cin >> words[i];

        sort(words.begin(), words.end(), custom_compare);

        cout << "year " << year << '\n';
        for (const string& word : words) cout << word << '\n';

        year++;
        cin >> n;
    }
    
    return EXIT_SUCCESS;
}