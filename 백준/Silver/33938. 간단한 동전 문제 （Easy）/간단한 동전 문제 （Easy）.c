#include <stdio.h>

int main(void) {
    int n, m;
    scanf("%d %d", &n, &m);
    
    int a, b;
    if (n == 0) {
        printf("%d\n", m == 0 ? 0 : -1);
        return 0;
    } 

    if (n == 1) {
        scanf("%d", &a);
        if (a == 0) { printf("%d\n", m == 0 ? 0 : -1); return 0; }
        if (m % a != 0) { printf("-1\n"); return 0; }
        int ans = m / a;
        printf("%d\n", ans >= 0 ? ans : -1);
        return 0;
    }

    scanf("%d %d", &a, &b);
    if (m == 0) { printf("0\n"); return 0; }

    if (a == 0 && b == 0) { printf("-1\n"); return 0; }

    if (a == 0 || b == 0) {
        int c = a + b;
        if (m % c != 0) { printf("-1\n"); return 0; }
        int y = m / c;
        printf("%d\n", y >= 0 ? y  : -1); return 0;
    }

    int best = -1;
    for (int t = 0; t <= 4000; t++) {
        int s = m - a * t;
        if (s % b != 0) continue;
        int candi = s / b;
        if (candi < 0) continue;
        candi += t;
        if (best == -1 || candi < best) best = candi;
    }

    printf("%d\n", best);
    return 0;
}