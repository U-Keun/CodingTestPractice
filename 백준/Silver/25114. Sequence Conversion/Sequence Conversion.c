#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int n; scanf("%d", &n);

    int *A = (int *) malloc(sizeof(int) * n),
        *B = (int *) malloc(sizeof(int) * n);

    for (int i = 0; i < n; i++) scanf("%d", &A[i]);
    for (int i = 0; i < n; i++) scanf("%d", &B[i]);

    int ans = 0;
    for (int i = 0; i < n - 1; i++) {
        if (A[i] == B[i]) continue;

        int x = A[i] ^ B[i];
        A[i] = B[i];
        A[i + 1] ^= x;
        ans++;
    }
    
    if (A[n - 1] == B[n - 1]) {
        printf("%d\n", ans);
    } else printf("-1\n");
    
    return 0;
}