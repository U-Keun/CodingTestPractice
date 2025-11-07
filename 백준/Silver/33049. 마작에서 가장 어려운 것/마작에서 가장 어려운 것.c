#include <stdio.h>

int main(void) {
    int p3, p4, p0;
    if (scanf("%d %d %d", &p3, &p4, &p0) != 3) return 0;

    for (int i = 0; i <= p0; i++) {
        int a = p3 + i;
        int b = p4 + (p0 - i);
        if (a % 3 == 0 && b % 4 == 0) {
            printf("%d %d\n", a / 3, b / 4);
            return 0;
        }
    }
    printf("-1\n");
    return 0;
}