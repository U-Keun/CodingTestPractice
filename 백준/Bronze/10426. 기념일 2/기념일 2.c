#include <stdio.h>

static inline int is_leap(int y) {
    return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
}

static const int days[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

static inline int mdays(int y, int m) {
    if (m != 2) {
        return days[m - 1];
    } else if (is_leap(y)) {
        return days[1] + 1;
    } else { return days[1]; }
}

int main(int argc, char *argv[]) {
    int y, m, d, n;
    scanf("%d-%d-%d %d", &y, &m, &d, &n);

    n--;
    while (n > 0) {
        int r = mdays(y, m) - d;
        if (r >= n) {
            d += n;
            n = 0;
        } else {
            m++;
            d = 1;
            n -= r + 1;
            if (m > 12) {
                y++;
                m %= 12;
            }
        }
    }

    printf("%04d-%02d-%02d\n", y, m, d);
}