#include <string>
#include <vector>

using namespace std;

int solution(int number, int limit, int power) {
    vector<int> rec(number + 1);
    for (int i = 1; i <= number; i++) rec[i] = i;
    for (int i = 2; i * i <= number; i++) {
        if (rec[i] == i) {
            for (int j = i * i; j <= number; j += i) {
                if (rec[j] == j) rec[j] = i;
            }
        }
    }
    
    int answer = 0, res;
    for (int i = 1; i <= number; i++) {
        int n = i;
        res = 1;
        while (n > 1) {
            int p = rec[n], cnt = 0;
            while (n % p == 0) {
                n /= p;
                cnt++;
            }
            res *= cnt + 1;
        }
        if (res > limit) answer += power;
        else answer += res;
    }
    
    return answer;
}