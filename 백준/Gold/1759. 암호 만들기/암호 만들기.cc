#include <iostream>
#include <vector>
#include <set>
#include <string>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int l, c;
vector<char> characters;
set<char> vowels = { 'a', 'e', 'i', 'o', 'u' };
vector<string> output;

bool contains_at_least_one_vowel(const string &password) {
    for (char c : password) {
        if (vowels.count(c)) return true;
    }
    return false;
}

bool contains_at_least_two_consonant(const string &password) {
    int count = 0;
    for (char c : password) {
        if (!vowels.count(c)) {
            count++;
            if (count >= 2) return true;
        }
    }
    return false;
}

void backtracking(int index, const string &password) {
    if (password.size() == l) {
        if (contains_at_least_one_vowel(password) && contains_at_least_two_consonant(password)) {
            output.push_back(password);
        }
        return;
    }
    if (index == c) return;

    if (password.size() < l) backtracking(index + 1, password + characters[index]);

    backtracking(index + 1, password);
}

int main() {
    FAST_IO

    cin >> l >> c;
    characters.resize(c);

    REP(i, 0, c - 1) cin >> characters[i];
    sort(characters.begin(), characters.end());
    backtracking(0, "");

    for (string s : output) {
        cout << s << '\n';
    }

    return EXIT_SUCCESS;
}