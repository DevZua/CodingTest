import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // dp[i][j]: i번째 열의 상태가 j일 때의 경우의 수
        // j = 0: 빈 칸, j = 1: 위에 사자, j = 2: 아래에 사자
        long[][] dp = new long[N+1][3];
        
        // 초기값 설정
        dp[1][0] = dp[1][1] = dp[1][2] = 1;
        
        // 동적 프로그래밍
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }
        
        // 결과 계산 및 출력
        long result = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
        System.out.println(result);
        
        br.close();
    }
}