#include <stdio.h>

long long mod_power(long long n, long long k, long long mod);

int main() {
    int T;
    long long N;
    
    scanf("%d", &T);
    while (T-- > 0) {
        scanf("%lld", &N);
        if (N == 1 || N == 2) {
            printf("1\n");
            continue;
        }
        printf("%lld\n", mod_power(2, N - 2, 1000000007));
    }
    
    return 0;
}

long long mod_power(long long n, long long k, long long mod) {
    long long answer = 1;
    n %= mod;
    while (k > 0) {
        if (k % 2 == 1) {
            answer = (answer * n) % mod;
        }
        n = (n * n) % mod;
        k /= 2;
    }
    return answer;
}