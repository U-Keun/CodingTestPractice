#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int binarySearch(const vector<int>& arr, int end, int key) {
    int start = 0;
    while (start < end) {
        int mid = (start + end) / 2;
        if (arr[mid] < key)
            start = mid + 1;
        else
            end = mid;
    }
    return start;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    vector<int> input(N), sequence(N, INT_MAX);
    
    for (int i = 0; i < N; i++) {
        cin >> input[i];
    }
    
    sequence[0] = input[0];
    int answer = 0;
    
    for (int i = 1; i < N; i++) {
        if (sequence[answer] < input[i]) {
            sequence[++answer] = input[i];
        } else {
            int idx = binarySearch(sequence, answer, input[i]);
            sequence[idx] = input[i];
        }
    }
    
    cout << answer + 1 << "\n";
    return 0;
}