#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> routes) {
    sort(routes.begin(), routes.end()); // 시작 지점이 작은 것부터
    
    vector<pair<int, int>> cameras;
    for (const auto& route : routes) {
        if (cameras.size() == 0) {
            cameras.push_back({ route[0], route[1] });
            continue;
        }
        
        bool needToAdd = true;
        for (auto& camera : cameras) {
            // 두 범위가 겹친다?
            if (camera.first <= route[1] && camera.second >= route[0]) {
                needToAdd = false;
                camera.first = max(camera.first, route[0]);
                camera.second = min(camera.second, route[1]);
            }
        }
        
        if (needToAdd) cameras.push_back({ route[0], route[1] });
    }

    return cameras.size();
}