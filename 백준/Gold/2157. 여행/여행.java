import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시 수
        int M = Integer.parseInt(st.nextToken()); // 최대 방문 도시 수
        int K = Integer.parseInt(st.nextToken()); // 항공편 수

        // 항공편 정보를 저장할 배열
        int[][] scoreBoard = new int[N + 1][N + 1]; // scoreBoard[i][j]: i->j 최대 점수
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            scoreBoard[from][to] = Math.max(scoreBoard[from][to], score); // 최대 점수 갱신
        }

        // DP 배열 정의: dp[i][j]는 i개의 도시를 거쳐 j번째 도시에 도달했을 때 최대 점수
        int[][] dp = new int[M + 1][N + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE); // 초기화: 불가능한 경로
        }
        dp[1][1] = 0; // 시작점 초기화

        // 동적 프로그래밍 수행
        for (int visitedCities = 1; visitedCities < M; visitedCities++) { // i개의 도시를 지남
            for (int currentCity = 1; currentCity <= N; currentCity++) { // j번째 도시에 대해
                if (dp[visitedCities][currentCity] == Integer.MIN_VALUE) continue; // 유효하지 않은 경로
                for (int nextCity = currentCity + 1; nextCity <= N; nextCity++) { // 다음 도시 탐색
                    if (scoreBoard[currentCity][nextCity] > 0) { // 연결된 경로만 처리
                        dp[visitedCities + 1][nextCity] = Math.max(dp[visitedCities + 1][nextCity],
                                dp[visitedCities][currentCity] + scoreBoard[currentCity][nextCity]);
                    }
                }
            }
        }

        // 최대 점수 계산
        int maxScore = 0;
        for (int visitedCities = 1; visitedCities <= M; visitedCities++) {
            maxScore = Math.max(maxScore, dp[visitedCities][N]);
        }

        // 결과 출력
        System.out.println(maxScore);
    }
}
