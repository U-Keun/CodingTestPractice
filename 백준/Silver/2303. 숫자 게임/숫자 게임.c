#include <stdio.h>

int main(void)
{
    int n;
    scanf("%d", &n);

    int cards[n][5];

    int record[n];
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 5; ++j) {
            scanf("%d", &cards[i][j]);
        }

        int max = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = j + 1; k < 4; k++) {
                for (int l = k + 1; l < 5; l++) {
                    int value = cards[i][j] + cards[i][k] + cards[i][l];
                    value %= 10;
                    if (max < value) max = value;
                }
            }
        }
        record[i] = max;
    }

    int score = 0, index = 0;
    for (int i = 0; i < n; ++i) {
        if (record[i] >= score) {
            score = record[i];
            index = i;
        }
    }

    printf("%d\n", index + 1);

    return 0;
}