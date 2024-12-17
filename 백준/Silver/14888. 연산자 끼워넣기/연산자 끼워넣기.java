import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] num;
    static int[] operator = new int[4];
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(num[0], 1);
        
        System.out.println(MAX);
        System.out.println(MIN);
    }
    
    private static void dfs(int now, int index) {
        if (index == N) {
            MAX = Math.max(MAX, now);
            MIN = Math.min(MIN, now);
            return;
        }
        
        // Addition
        if (operator[0] > 0) {
            operator[0]--;
            dfs(now + num[index], index + 1);
            operator[0]++;
        }
        
        // Subtraction
        if (operator[1] > 0) {
            operator[1]--;
            dfs(now - num[index], index + 1);
            operator[1]++;
        }
        
        // Multiplication
        if (operator[2] > 0) {
            operator[2]--;
            dfs(now * num[index], index + 1);
            operator[2]++;
        }
        
        // Division
        if (operator[3] > 0) {
            operator[3]--;
            // Handle division by zero and integer division for negative numbers
            dfs(now / num[index], index + 1);
            operator[3]++;
        }
    }
}