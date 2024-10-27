#include <iostream>
#include <unordered_map>
#include <vector>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    string last;
    cin >> last;

    int n;
    cin >> n;
    unordered_map<char, vector<string>> list;
    string input;
    for (int i = 0; i < n; i++) {
        cin >> input;
        list[input[0]].push_back(input);
    }

    char last_char = last[last.size() - 1];

    if (list[last_char].size() == 0) {
        cout << '?';
        return 0;
    }

    string answer = list[last_char][0];
    bool checkmate = false;
    for (string& word : list[last_char]) {
        char next_char = word[word.size() - 1];
        if (list[next_char].size() == 0 ||
            (last_char == next_char && list[next_char].size() == 1)) {
            answer = word;
            checkmate = true;
            break;
        }
    }

    cout << answer;
    if (checkmate) cout << '!';

    return 0;
}