#include <stdio.h>

long long mod_power(long long n, long long k, long long mod);

int main() {
    long long a, b;
    scanf("%lld", &a);
    scanf("%lld", &b);
    
    printf("%lld\n", mod_power(a, b, 1000000007));

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
    return answer % mod;
}