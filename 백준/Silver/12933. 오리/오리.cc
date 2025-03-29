#include <iostream>
#include <string>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

static const string pattern = "quack";
vector<int> ducks;

int main() {
    FAST_IO;

    string input; cin >> input;
    
    for (char c : input) {
        bool matched = false;
        for (int &state : ducks) {
            if (pattern[state] == c) {
                state = (state + 1) % 5;
                matched = true;
                break;
            }
        }

        if (!matched) {
            if (c == 'q') ducks.push_back(1);
            else {
                cout << -1 << '\n';
                return EXIT_SUCCESS;
            }
        }
    }

    for (int state : ducks) {
        if (state != 0) {
            cout << -1 << '\n';
            return EXIT_SUCCESS;
        }
    }

    cout << ducks.size() << '\n';

    return EXIT_SUCCESS;
}