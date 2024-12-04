#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int max_, min_, sum_;
vector<int> numbers;

int mode() {
    int k = max_ - min_ + 1;
    vector<int> arr(k);
    if (numbers.size() == 1) return numbers[0];
    for (int num : numbers) {
        arr[num - min_]++;
    }

    int min1 = 4001, min2 = 4001, frequency = 1;
    REP(i, 0, k - 1) {
        if (arr[i] > frequency) {
            frequency = arr[i];
            min1 = i + min_;
            min2 = 4001;
        } else if (arr[i] == frequency) {
            if (i + min_ < min1) {
                min2 = min1;
                min1 = i + min_;
            }
            else if (i + min_ < min2) min2 = i + min_;
        }
    }
    if (min2 == 4001) return min1;
    else if (arr[min1 - min_] != arr[min2 - min_]) return min1;
    return min2;
}

int main() {
    FAST_IO

    int n; cin >> n;
    max_ = -4000;
    min_ = 4000;
    sum_ = 0;
    numbers.resize(n);
    REP(i, 0, n - 1) {
        cin >> numbers[i];
        sum_ += numbers[i];
        max_ = max(max_, numbers[i]);
        min_ = min(min_, numbers[i]);
    }

    cout << lround(sum_ / (double) n) << '\n';
    sort(numbers.begin(), numbers.end());
    cout << numbers[n / 2] << '\n';
    cout << mode() << '\n';
    cout << max_ - min_ << '\n';

    return EXIT_SUCCESS;
}