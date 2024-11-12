import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생의 수
        int m = Integer.parseInt(st.nextToken()); // 키 비교 횟수
        
        boolean[][] reachability = new boolean[n + 1][n + 1];
        
        // 비교 결과 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());
            reachability[smaller][taller] = true;
        }
        
        // 플로이드-워셜 알고리즘을 통해 모든 경로에 대해 도달 가능 여부 계산
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (reachability[i][k] && reachability[k][j]) {
                        reachability[i][j] = true;
                    }
                }
            }
        }
        
        int count = 0;
        
        // 각 학생마다 자신보다 키가 큰 사람과 작은 사람이 모두 있는지 확인
        for (int i = 1; i <= n; i++) {
            boolean canDetermineRank = true;
            for (int j = 1; j <= n; j++) {
                if (i != j && !reachability[i][j] && !reachability[j][i]) {
                    canDetermineRank = false;
                    break;
                }
            }
            if (canDetermineRank) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}
