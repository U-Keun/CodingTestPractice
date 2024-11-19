#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    cin >> n;
    vector<long long> numbers(n);
    REP(i, 0, n - 1) cin >> numbers[i];
    sort(numbers.begin(), numbers.end());

    long long result = 4000000000;
    int answer[3];
    REP(i, 0, n - 3) {
        int left = i + 1, right = n - 1;
        while (left < right) {
            long long val = numbers[i] + numbers[left] + numbers[right];
            if (abs(val) < result) {
                answer[0] = numbers[i];
                answer[1] = numbers[left];
                answer[2] = numbers[right];
                result = abs(val);
            }

            if (val < 0) left++;
            else if (val > 0) right--;
            else break;
        }
    }

    cout << answer[0] << ' '
        << answer[1] << ' '
        << answer[2];

    return 0;
}