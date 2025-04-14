import java.util.*;

class Solution {
    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dy = {0, 1, 0, -1};
    static boolean[][][] visited;

    public int[] solution(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        visited = new boolean[rows][cols][4];

        List<Integer> result = new ArrayList<>();

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[x][y][d]) {
                        result.add(getCycleLength(grid, x, y, d));
                    }
                }
            }
        }

        Collections.sort(result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private int getCycleLength(String[] grid, int x, int y, int dir) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length();

        while (!visited[x][y][dir]) {
            visited[x][y][dir] = true;
            count++;

            char command = grid[x].charAt(y);

            if (command == 'L') {
                dir = (dir + 3) % 4;
            } else if (command == 'R') {
                dir = (dir + 1) % 4;
            }

            x = (x + dx[dir] + rows) % rows;
            y = (y + dy[dir] + cols) % cols;
        }

        return count;
    }
}
