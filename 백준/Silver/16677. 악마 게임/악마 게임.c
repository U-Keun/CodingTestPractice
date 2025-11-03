#include <stdio.h>
#include <string.h>

int contains_substring(const char *buf, const char *tar) {
    if (!buf || !tar) return -1;
    if (tar[0] == '\0') return 0;

    int i = 0, j = 0;
    while (buf[i] != '\0' && tar[j] != '\0') {
        if (buf[i] == tar[j]) { j++; }
        i++;
    }
    return tar[j] == '\0';
}

int main(void) {
    char target[101];
    scanf("%100s", target);

    int n; scanf("%d", &n);

    char buf[101];
    double score = 0;
    int num;
    char ans[101];
    while (n--) {
        scanf("%100s %d", buf, &num);
        if (contains_substring(buf, target) == 1) {
            double cur = num / (double) (strlen(buf) - strlen(target));
            if (score < cur) { 
                strcpy(ans, buf);
                score = cur;
            }
        }
    }

    if (score) {
        printf("%s\n", ans);
    } else { printf("No Jam\n"); }
}