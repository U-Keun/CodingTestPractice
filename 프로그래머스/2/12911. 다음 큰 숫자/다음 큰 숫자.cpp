#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int power_of_2(int x) {
    if (x == 0) return 1;
    return power_of_2(x - 1) * 2;
}

int solution(int n) {
    int l = 0;
    vector<int> idx;
    while (n > 0) {
        if (n & 1) idx.push_back(l);
        n >>= 1;
        l++;
    }
    reverse(idx.begin(), idx.end());
    int s = idx.size(), i = s - 1;
    while (i > 0 && idx[i] == idx[i - 1] - 1) i--;
    int answer = 0;
    if (i == 0) {
        answer += power_of_2(idx[0] + 1);
        for (int j = 1; j < s; j++) answer += power_of_2(j - 1);
    } else {
        for (int j = 0; j < i; j++) answer += power_of_2(idx[j]);
        answer += power_of_2(idx[i++] + 1);
        int m = 0;
        while (i < s) {
            answer += power_of_2(m++);
            i++;
        }
    }
    
    return answer;
}