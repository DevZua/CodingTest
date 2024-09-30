import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        List<int[]> shortcuts = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            
            if (end <= D) {
                shortcuts.add(new int[]{start, end, length});
            }
        }
        
        int[] dp = new int[D + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= D; i++) {
            dp[i] = Math.min(dp[i], dp[i-1] + 1);
            
            for (int[] shortcut : shortcuts) {
                if (shortcut[1] == i) {
                    dp[i] = Math.min(dp[i], dp[shortcut[0]] + shortcut[2]);
                }
            }
        }
        
        System.out.println(dp[D]);
    }
}