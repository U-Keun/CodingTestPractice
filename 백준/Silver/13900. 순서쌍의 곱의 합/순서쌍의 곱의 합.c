#include <stdio.h>

int main(int argc, char *argv[]) {
    int n;
    scanf("%d", &n);
    
    long long ans = 0, sum = 0;
    int num;
    while (n--) {
        scanf("%d", &num);
        if (sum == 0) {
            sum += num;
            continue;
        }

        ans += sum * num;
        sum += num;
    }

    printf("%lld\n", ans);
}