import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[][] dp;
    static List<int[]> road; // 공사 구간 정보를 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: 도시 크기 N, M
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 공사 구간 수 입력
        K = Integer.parseInt(br.readLine());

        // DP 배열 및 공사 구간 초기화
        dp = new long[M + 1][N + 1];
        road = new ArrayList<>();

        dp[0][0] = 1; // 시작점 초기화

        // 공사 구간 정보 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            road.add(new int[]{a, b, c, d});
        }

        // DP 계산
        for (int x = 0; x <= M; x++) {
            for (int y = 0; y <= N; y++) {
                // 위쪽에서 오는 경우
                if (y > 0) {
                    boolean isBlocked = false;
                    for (int[] r : road) {
                        if (check(y - 1, x, y, x, r)) {
                            isBlocked = true;
                            break;
                        }
                    }
                    if (!isBlocked) {
                        dp[x][y] += dp[x][y - 1];
                    }
                }

                // 왼쪽에서 오는 경우
                if (x > 0) {
                    boolean isBlocked = false;
                    for (int[] r : road) {
                        if (check(y, x - 1, y, x, r)) {
                            isBlocked = true;
                            break;
                        }
                    }
                    if (!isBlocked) {
                        dp[x][y] += dp[x - 1][y];
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(dp[M][N]);
    }

    // 공사 구간 확인 함수
    static boolean check(int y1, int x1, int y2, int x2, int[] r) {
        return (y1 == r[0] && x1 == r[1] && y2 == r[2] && x2 == r[3]) ||
               (y1 == r[2] && x1 == r[3] && y2 == r[0] && x2 == r[1]);
    }
}
