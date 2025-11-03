#include <stdio.h>

int main(int argc, char *argv[]) {
    char buf[5];
    fgets(buf, sizeof(buf), stdin);

    char *ans;
    if (buf[0] == 'N') {
        ans = "North London Collegiate School";
    } else if (buf[0] == 'B') {
        ans = "Branksome Hall Asia";
    } else if (buf[0] == 'K') {
        ans = "Korea International School";
    } else { // buf[0] == 'S'
        ans = "St. Johnsbury Academy";
    }
    
    printf("%s\n", ans);
}