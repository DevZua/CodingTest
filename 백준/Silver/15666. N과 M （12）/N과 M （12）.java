import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 입력 크기
    static int[] arr; // 입력된 숫자 배열
    static int[] answer; // 선택된 수열을 저장할 배열
    static StringBuilder sb; // 결과 출력을 위한 StringBuilder

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 숫자 배열 입력 및 정렬
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        // 결과 초기화
        answer = new int[m];
        sb = new StringBuilder();

        // 백트래킹 시작
        backtrack(0, 0);

        // 최종 결과 출력
        System.out.print(sb);
    }

    static void backtrack(int k, int idx) {
        // 수열이 완성된 경우
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(answer[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 백트래킹을 위한 중복 방지 변수
        int temp = 0;

        // 현재 위치부터 가능한 모든 숫자 탐색
        for (int i = idx; i < n; i++) {
            if (temp != arr[i]) { // 중복 숫자 방지
                answer[k] = arr[i]; // 현재 숫자를 선택
                temp = arr[i]; // 중복 확인용 변수 갱신
                backtrack(k + 1, i); // 다음 숫자 탐색
            }
        }
    }
}
