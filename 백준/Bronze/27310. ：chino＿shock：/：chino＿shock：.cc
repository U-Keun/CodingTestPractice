#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    string input;
    cin >> input;

    int l = input.size(), answer = l;
    for (int i = 0; i < l; i++) {
        if (input[i] == ':') answer++;
        else if (input[i] == '_') answer += 5;
    }

    cout << answer;

    return 0;
}