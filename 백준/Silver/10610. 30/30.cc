#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    string input; cin >> input;
    int n = input.size();

    vector<int> numbers;
    int check = 0;
    REP(i, 0, n - 1) {
        int val = input[i] - '0';
        check += val;
        numbers.push_back(val);
    }

    sort(numbers.begin(), numbers.end(), greater<>());

    if (check % 3 != 0 || numbers[n - 1]) cout << -1;
    else {
        for (auto &num : numbers) cout << num;
    }

   return EXIT_SUCCESS;
}