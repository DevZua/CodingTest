import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 물건의 개수
        int C = Integer.parseInt(st.nextToken());  // 제한 무게

        int[] weights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬 (이진 탐색 또는 투포인터 사용 가능)
        Arrays.sort(weights);

        // 1개 조합 확인
        for (int weight : weights) {
            if (weight == C) {
                System.out.println(1);
                return;
            }
        }

        // 2개 및 3개 조합 확인
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = weights[i] + weights[j];

                if (sum == C) {  // 2개 조합으로 해결 가능
                    System.out.println(1);
                    return;
                }

                if (sum < C) {
                    int remaining = C - sum;

                    // 3번째 물건을 찾기 위해 이진 탐색
                    if (Arrays.binarySearch(weights, j + 1, N, remaining) >= 0) {
                        System.out.println(1);
                        return;
                    }
                }
            }
        }

        // 조합 불가능
        System.out.println(0);
    }
}
