#include <stdio.h>

int main(void) {
    int n, k;
    scanf("%d %d", &n, &k);

    int male[7] = { 0 }, female[7] = { 0 }, 
        s, y;
    while (n--) {
        scanf("%d %d", &s, &y);
        if (s) {
            male[y]++;
        } else female[y]++;
    }

    int ans = 0;
    for (int i = 1; i <= 6; i++) {
        ans += male[i] / k + (male[i] % k == 0 ? 0 : 1);
        ans += female[i] / k + (female[i] % k == 0 ? 0 : 1);
    }

    printf("%d\n", ans);

    return 0;
}