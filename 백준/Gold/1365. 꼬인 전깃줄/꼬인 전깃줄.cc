#include <iostream>
#include <vector>
using namespace std;

int binarySearch(const vector<int>& lis, int target) {
    int left = 0, right = lis.size();
    while (left < right) {
        int mid = (left + right) / 2;
        if (lis[mid] < target)
            left = mid + 1;
        else
            right = mid;
    }
    return left;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    vector<int> numbers(N);
    for (int i = 0; i < N; i++) {
        cin >> numbers[i];
    }
    
    vector<int> lis;
    for (int i = 0; i < N; i++) {
        int index = binarySearch(lis, numbers[i]);
        if (index >= lis.size()) {
            lis.push_back(numbers[i]);
        } else {
            lis[index] = numbers[i];
        }
    }
    
    cout << (N - lis.size()) << "\n";
    return 0;
}