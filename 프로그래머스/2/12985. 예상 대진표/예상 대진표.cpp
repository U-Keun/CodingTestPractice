#include <algorithm>

using namespace std;

int solution(int n, int a, int b)
{
    int answer = 0;
    if (a > b) swap(a, b);
    while (a < b) {
        a = a / 2 + a % 2;
        b = b / 2 + b % 2;
        answer++;
    }
    return answer;
}