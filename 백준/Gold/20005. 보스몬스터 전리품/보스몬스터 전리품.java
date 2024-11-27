import java.util.*;

public class Main {

    static int m, n, p;
    static Map<Character, Player> playerMap = new HashMap<>();
    static char[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 지도 크기와 플레이어 수 입력
        m = scan.nextInt();
        n = scan.nextInt();
        p = scan.nextInt();

        board = new char[m][n];
        Queue<Node> queue = new LinkedList<>();

        // 지도 입력 및 초기 플레이어 위치 큐에 삽입
        for (int i = 0; i < m; i++) {
            String line = scan.next();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
                if (Character.isLowerCase(board[i][j])) { // 플레이어 위치
                    queue.offer(new Node(board[i][j], i, j));
                }
            }
        }

        // 플레이어 정보 입력
        for (int i = 0; i < p; i++) {
            char id = scan.next().charAt(0);
            int dps = scan.nextInt();
            playerMap.put(id, new Player(dps));
        }

        // 보스 체력 입력
        int bossHp = scan.nextInt();

        // 결과 출력
        System.out.println(bfs(queue, bossHp));
    }

    static int bfs(Queue<Node> queue, int bossHp) {
        boolean[][][] visited = new boolean[m][n][26]; // 플레이어별 방문 체크
        Set<Character> attackers = new HashSet<>();   // 보스를 공격할 수 있는 플레이어 집합

        while (bossHp > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                char playerId = current.id;

                // 보스를 이미 공격 중인 플레이어는 건너뜀
                if (attackers.contains(playerId)) continue;

                // 네 방향으로 탐색
                for (int dir = 0; dir < 4; dir++) {
                    int nx = current.x + dx[dir];
                    int ny = current.y + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue; // 범위 초과
                    if (visited[nx][ny][playerId - 'a'] || board[nx][ny] == 'X') continue; // 이미 방문 또는 장애물

                    visited[nx][ny][playerId - 'a'] = true;

                    if (board[nx][ny] == 'B') { // 보스 위치 도달
                        attackers.add(playerId);
                    } else {
                        queue.offer(new Node(playerId, nx, ny)); // 다음 위치 탐색
                    }
                }
            }

            // 모든 공격 중인 플레이어로부터 보스 공격
            for (char attacker : attackers) {
                bossHp -= playerMap.get(attacker).dps;
                if (bossHp <= 0) break; // 보스 체력이 0 이하가 되면 종료
            }
        }

        return attackers.size();
    }

    static class Node {
        char id;
        int x, y;

        Node(char id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    static class Player {
        int dps;

        Player(int dps) {
            this.dps = dps;
        }
    }
}
