#include <stdio.h>
#include <stdlib.h>

int compare(const void* a, const void* b) {
    int int_a = *((int*)a);
    int int_b = *((int*)b);

    if (int_a < int_b) return -1;
    else if (int_a > int_b) return 1;
    else return 0;
}

int main(void)
{
    int n;
    scanf("%d", &n);

    int seq[n];
    for (int i = 0; i < n; i++) {
        scanf("%d", &seq[i]);
    }

    qsort(seq, n, sizeof(int), compare);

    for (int i = 0; i < n; i++) {
        printf("%d\n", seq[i]);
    }

    return 0;
}