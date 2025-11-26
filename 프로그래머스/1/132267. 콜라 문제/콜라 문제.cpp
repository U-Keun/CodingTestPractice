#include <string>
#include <vector>

using namespace std;

int solution(int a, int b, int n) {
    int answer = 0;
    while (n >= a) {
        int d = (n / a) * b;
        answer += d;
        n %= a;
        n += d;
    }
    return answer;
}