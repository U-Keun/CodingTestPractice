#include <iostream>
#include <vector>
#include <climits>
#include <stack>

using namespace std;

int binarySearch(const vector<int>& arr, int end, int key) {
    int start = 0;
    while (start < end) {
        int mid = (start + end) / 2;
        if (arr[mid] < key) start = mid + 1;
        else end = mid;
    }
    return start;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N; cin >> N;
    
    vector<int> input(N);
    for (int i = 0; i < N; i++) cin >> input[i];
    
    // sequence: 각 길이의 증가하는 부분 수열의 최소 마지막 원소 값을 저장
    // maxLength: 해당 인덱스까지 고려했을 때, 만들 수 있는 증가하는 부분 수열의 길이
    vector<int> sequence(N, INT_MAX);
    vector<int> maxLength(N, 0);
    
    sequence[0] = input[0];
    maxLength[0] = 1;
    int answer = 0; // answer는 sequence의 마지막 인덱스, 즉 최대 부분 수열 길이는 answer + 1

    for (int i = 1; i < N; i++) {
        if (sequence[answer] < input[i]) {
            sequence[++answer] = input[i];
            maxLength[i] = answer + 1;
        } else {
            int idx = binarySearch(sequence, answer, input[i]);
            sequence[idx] = input[i];
            maxLength[i] = idx + 1;
        }
    }
    
    // 증가하는 부분 수열을 재구성하기 위해 스택을 사용 (역순으로 찾은 후 올바른 순서로 출력)
    stack<int> s;
    int currentLength = answer + 1;
    int maxLengthIdx = N - 1;
    while (maxLengthIdx >= 0 && currentLength > 0) {
        if (maxLength[maxLengthIdx] == currentLength) {
            s.push(input[maxLengthIdx]);
            currentLength--;
        }
        maxLengthIdx--;
    }
    
    cout << answer + 1 << "\n";
    while (!s.empty()) {
        cout << s.top() << " ";
        s.pop();
    }
    cout << "\n";
    
    return 0;
}