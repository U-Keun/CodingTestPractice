#include <iostream>
#include <map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    string input; cin >> input;
    map<string, int> _map;
    int n = input.size() / 3;
    for (int i = 0; i < n; i++) {
        _map[input.substr(i * 3, 3)]++;
    }

    map<char, int> answer = { { 'P', 13 }, { 'K', 13 }, { 'H', 13 }, { 'T', 13 } };
    for (const auto& [key, value] : _map) {
        if (value > 1) {
            cout << "GRESKA\n";
            return EXIT_SUCCESS;
        }
        answer[key[0]]--;
    }
    cout << answer['P'] << ' '
        << answer['K'] << ' '
        << answer['H'] << ' '
        << answer['T'] << '\n';

    return EXIT_SUCCESS;
}