#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO;
    short n;
    cin >> n;

    vector<long long> alphabet(26);

    vector< vector<char> > input(n, vector<char>(10));

    string str;
    int l, idx;
    for (int i = 0; i < n; i++) {
        cin >> str;
        l = str.size();
        idx = 9;
        for (int j = l - 1; j >= 0; j--) { // j는 주어진 문자열의 맨 뒤 인덱스부터
            input[i][idx--] = str[j];
        }
    }

    long long decimal = 1000000000;
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < n; j++) {
            if (input[j][i] - 'A' < 0) continue;
            alphabet[input[j][i] - 'A'] += decimal;
        }
        decimal /= 10;
    }

    sort(alphabet.begin(), alphabet.end(), greater<long long>());
    long long answer = 0;
    int max = 9, k = 0;
    while (alphabet[k] > 0) {
        answer += max * alphabet[k];
        max--;
        k++;
    }

    cout << answer << '\n';

    return 0;
}