#include <iostream>
#include <map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n, a, b; cin >> n >> a >> b;
    map<int, char> record;
    while (a--) {
        int num; cin >> num;
        record[num] = 'a';
    }
    while (b--) {
        int num; cin >> num;
        if (record[num] == 'a') {
            record[num] = 'c';
        } else record[num] = 'b';
    }

    char cur = ' ';
    int answer = 0;
    for (const auto& tmp : record) {
        if (tmp.second == 'c') continue;
        if (cur == ' ') {
            cur = tmp.second;
            continue;
        }
        if (cur != tmp.second) {
            answer++;
            cur = tmp.second;
        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}