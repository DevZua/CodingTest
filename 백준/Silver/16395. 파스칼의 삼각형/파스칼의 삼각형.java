import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        long[][] pascal = new long[31][31];
        
        // 파스칼 삼각형 초기화
        for (int i = 0; i <= 30; i++) {
            pascal[i][0] = 1;
            pascal[i][i] = 1;
        }
        
        // 파스칼 삼각형 계산
        for (int i = 2; i <= 30; i++) {
            for (int j = 1; j < i; j++) {
                pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
            }
        }
        
        // n-1 행, k-1 열의 값 출력 (인덱스 조정)
        System.out.println(pascal[n-1][k-1]);
    }
}