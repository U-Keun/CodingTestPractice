#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0, cur = 1, i = 1, j = 2;
    while (i != j) {
        if (cur < n) {
            cur += j++;
        } else if (cur > n) {
            cur -= i++;
        } else {
            answer++;
            cur += j++;
            cur -= i++;
        }
    }
    
    return answer;
}