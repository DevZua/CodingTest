import java.util.*;

class Solution {
    int n, m;
    String[][] map;
    boolean[][] visited;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        map = new String[n + 2][m + 2];

        // 1. 바깥 테두리를 -1로 채움
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(map[i], "-1");
        }

        // 2. storage를 map에 삽입 (1행 1열부터 시작)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = String.valueOf(storage[i].charAt(j));
            }
        }

        // 3. 요청 순서대로 처리
        for (String request : requests) {
            bfsFromOutside(); // 외부에서 접근 가능한 영역 갱신

            if (request.length() == 2) {
                // 크레인: 해당 알파벳 모두 제거
                removeAll(String.valueOf(request.charAt(0)));
            } else {
                // 지게차: 접근 가능한 것만 제거
                removeAccessible(String.valueOf(request.charAt(0)));
            }
        }

        // 4. 남은 컨테이너 개수 세기
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!map[i][j].equals("-1")) {
                    answer++;
                }
            }
        }
        return answer;
    }

    // 외부에서 접근 가능한 영역 탐색 (BFS)
    void bfsFromOutside() {
        visited = new boolean[n + 2][m + 2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= m + 2 || ny >= n + 2) continue;
                if (visited[ny][nx]) continue;
                if (!map[ny][nx].equals("-1")) continue;
                visited[ny][nx] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
    }

    // 크레인: 해당 알파벳 모두 제거
    void removeAll(String ch) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j].equals(ch)) {
                    map[i][j] = "-1";
                }
            }
        }
    }

    // 지게차: 외부에서 접근 가능한 곳만 제거
    void removeAccessible(String ch) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j].equals(ch) && isConnected(i, j)) {
                    map[i][j] = "-1";
                }
            }
        }
    }

    // 4방향 중 외부(-1)에 연결되어 있으면 true
    boolean isConnected(int y, int x) {
        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (visited[ny][nx]) return true;
        }
        return false;
    }
}
