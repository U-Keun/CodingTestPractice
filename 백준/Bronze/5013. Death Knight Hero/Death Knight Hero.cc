#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int answer = 0;
    string input;
    while (n-- > 0) {
        cin >> input;
        int l = input.size();
        bool win = true;
        for (int i = 0; i < l - 1; i++) {
            if (input.at(i) == 'C' && input.at(i + 1) == 'D') {
                win = false;
                break;
            }
        }

        if (win) answer++;
    }
    cout << answer << '\n';
    return 0;
}