#include <iostream>
#include <sstream>
#include <vector>
#include <string>
using namespace std;

// 한 줄의 문자열을 공백 기준으로 파싱하여 정수 벡터로 변환하는 헬퍼 함수
inline vector<int> readIntegers(const string &input) {
    vector<int> nums;
    istringstream iss(input);
    int num;
    while (iss >> num) {
        nums.push_back(num);
    }
    return nums;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string line;
    
    // 첫 줄: 방의 크기 N x M
    getline(cin, line);
    vector<int> NM = readIntegers(line);
    int N = NM[0], M = NM[1];

    // 두 번째 줄: 시작 위치와 방향 (phase: row, col, dir)
    getline(cin, line);
    vector<int> phase = readIntegers(line);
    // 방향: 0 - 북, 1 - 동, 2 - 남, 3 - 서

    // room 배열 입력 (N행, M열)
    vector<vector<int>> room(N, vector<int>(M));
    for (int i = 0; i < N; i++) {
        getline(cin, line);
        room[i] = readIntegers(line);
    }

    int answer = 0;

    // 미리 정의된 방향 배열: 0: 북, 1: 동, 2: 남, 3: 서
    const int directions[4][2] = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

    // 인접한 칸 중 아직 청소하지 않은(값이 0인) 칸이 있는지 검사하는 람다 함수
    auto isMovable = [&](int r, int c) -> bool {
        for (int d = 0; d < 4; d++) {
            int nr = r + directions[d][0];
            int nc = c + directions[d][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] == 0)
                return true;
        }
        return false;
    };

    while (true) {
        int r = phase[0], c = phase[1], d = phase[2];

        // 현재 위치가 아직 청소되지 않았다면 청소(2로 표시)하고 answer 증가
        if (room[r][c] == 0) {
            room[r][c] = 2;
            ++answer;
        }

        if (isMovable(r, c)) {
            // 왼쪽으로 회전: (d - 1)를 모듈로 4로 계산하여 구현
            d = (d + 3) % 4;
            phase[2] = d;
            int nr = r + directions[d][0];
            int nc = c + directions[d][1];
            // 왼쪽 방향 칸이 범위 내이고 아직 청소되지 않았다면 전진
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] == 0) {
                phase[0] = nr;
                phase[1] = nc;
            }
            continue;
        }

        // 인접 칸에 이동할 곳이 없는 경우, 현재 방향의 반대쪽(후진) 칸 계산
        int back_d = (d + 2) % 4;
        int nr = r + directions[back_d][0];
        int nc = c + directions[back_d][1];
        // 후진 칸이 범위를 벗어나거나 벽(값이 1)이면 종료
        if (nr < 0 || nr >= N || nc < 0 || nc >= M || room[nr][nc] == 1) {
            break;
        }
        // 후진 가능하면 후진
        phase[0] = nr;
        phase[1] = nc;
    }

    cout << answer << "\n";
    return 0;
}