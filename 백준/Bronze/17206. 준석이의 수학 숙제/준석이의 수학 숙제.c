#include <stdio.h>

int main(int argc, char *argv[]) {
    int t;
    scanf("%d", &t);

    int ans[80001], cur = 1;
    while (t--) {
        int num;
        scanf("%d", &num);
        while (num >= cur) {
            ans[cur] = ans[cur - 1];
            if (cur % 3 == 0 || cur % 7 == 0) ans[cur] += cur;
            cur++;
        }
        printf("%d\n", ans[num]);
    }
}