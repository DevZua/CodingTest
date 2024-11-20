import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); 
        
        int[] health = new int[N + 1];
        int[] joy = new int[N + 1];
        
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            health[i] = Integer.parseInt(st1.nextToken());
            joy[i] = Integer.parseInt(st2.nextToken());
        }
        
        int maxHealth = 100;
        
        int[][] dp = new int[N + 1][maxHealth];
        
        
        for (int i = 1; i <= N; i++) {
            for (int h = 0; h < maxHealth; h++) {
                if (health[i] <= h) {
                    dp[i][h] = Math.max(dp[i - 1][h], dp[i - 1][h - health[i]] + joy[i]);
                } else {
                    dp[i][h] = dp[i - 1][h];
                }
            }
        }
        

        int answer = 0;
        for (int h = 0; h < maxHealth; h++) {
            answer = Math.max(answer, dp[N][h]);
        }
        System.out.println(answer);
    }
}
