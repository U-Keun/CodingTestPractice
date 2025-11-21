#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(long long n) {
    vector<int> nums;
    while (n > 0) {
        nums.push_back(n % 10);
        n /= 10;
    }
    sort(nums.begin(), nums.end(), greater<int>());
    long long answer = 0;
    int size = nums.size();
    for (int i = 0; i < size; i++) {
        answer += nums[i];
        if (i < size - 1) answer *= 10;
    }
    return answer;
}