#include <stdio.h>

const long long MOD = 1000000007;

long long mod_power(long long n, long long k, long long mod);

int main() {
    long long N;
    scanf("%lld", &N);
    
    if (N == 0) {
        printf("%d\n", 1);
        return 0;
    }
    
    printf("%lld\n", mod_power(2, N - 1, MOD));
    return 0;
}

long long mod_power(long long n, long long k, long long mod) {
    long long answer = 1;
    n %= mod;
    while (k > 0) {
        if (k % 2 == 1) {
            answer *= n;
            answer %= mod;
        }
        n *= n;
        n %= mod;
        k /= 2;
    }
    return answer;
}