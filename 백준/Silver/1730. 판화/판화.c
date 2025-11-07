#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int n; scanf("%d", &n);
    char buf[251]; 
    if (scanf("%s", buf) != 1) buf[0] = '\0';

    unsigned char **g = (unsigned char**) malloc(sizeof(unsigned char*) * n);
    for (int i = 0; i < n; i++) g[i] = (unsigned char*) calloc(n, 1);

    int x = 0, y = 0;
    for (char *p = buf; *p; p++) {
        int nx = x, ny = y;
        if (*p == 'U') ny--;
        else if (*p == 'D') ny++;
        else if (*p == 'L') nx--;
        else nx++;

        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

        if (*p == 'U' || *p == 'D') {
            g[y][x] |= 2;
            g[ny][nx] |= 2;
        } else {
            g[y][x] |= 1;
            g[ny][nx] |= 1;
        }
        x = nx; y = ny;
    }

    for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
            unsigned char v = g[r][c];
            putchar(v == (1 | 2) ? '+' : v == 2 ? '|' : v == 1 ? '-' : '.');
        }
        putchar('\n');
    }

    for (int i = 0; i < n; i++) free(g[i]);
    free(g);

    return 0;
}