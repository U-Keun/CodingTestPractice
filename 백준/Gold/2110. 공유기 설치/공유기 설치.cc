#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int count_houses(const vector<int> &houses, int distance) {
    int pointer = 0, answer = 1, n = houses.size();

    REP(i, 0, n - 1) {
        if(houses[i] - houses[pointer] >= distance) {
            pointer = i;
            answer++;
        }
    }
    return answer;
}

int main() {
    FAST_IO

    int n, c; cin >> n >> c;
    vector<int> houses(n);
    REP(i, 0, n - 1) cin >> houses[i];
    sort(houses.begin(), houses.end());
    int low = 1,
        high = houses[n - 1] - houses[0],
        answer = 0;

    while(low <= high) {
        int mid = (low + high) / 2;
        if(count_houses(houses, mid) >= c) {
            answer = mid;
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    cout << answer;
    return EXIT_SUCCESS;
}