#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int t; cin >> t; 
    vector<int> arr;
    vector<long long> answer;
    long long  max_til_cur, max_end;
    while (t) {
        arr.resize(t);
        for (int i = 0; i < t; i++) cin >> arr[i];       

        max_til_cur = max_end = arr[0];
        for (int i = 1; i < t; i++) {
            max_til_cur = max(max_til_cur + arr[i], (long long) arr[i]);
            max_end = max(max_end, max_til_cur);
        }
        
        answer.push_back(max_end);
        cin >> t;
    }

    for (long long ans : answer) cout << ans << '\n';

    return EXIT_SUCCESS;
}