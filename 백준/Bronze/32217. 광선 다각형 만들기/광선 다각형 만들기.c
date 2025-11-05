#include <stdio.h>

int main(void) {
    int n;
    scanf("%d", &n);
    
    int ans = 180 * (n - 1), ang;
    while (n--) {
        scanf("%d", &ang);
        ans -= ang * 2;
    }

    printf("%d\n", ans);

    return 0;
}