import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 숫자 배열 입력
        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 배열 정렬 (사전 순 출력을 위해)
        Arrays.sort(numbers);

        // 결과 저장용 Set 및 StringBuilder
        Set<String> resultSet = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();

        // 백트래킹 구현
        int[] sequence = new int[M]; // 수열 저장 배열
        backtrack(numbers, sequence, 0, 0, N, M, resultSet);

        // 결과 출력
        for (String s : resultSet) {
            sb.append(s).append('\n');
        }
        System.out.print(sb);
    }

    // 백트래킹 함수
    static void backtrack(int[] numbers, int[] sequence, int depth, int start, int N, int M, Set<String> resultSet) {
        if (depth == M) {
            // 수열을 문자열로 만들어 Set에 저장 (중복 제거)
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < M; i++) {
                temp.append(sequence[i]).append(' ');
            }
            resultSet.add(temp.toString());
            return;
        }

        for (int i = start; i < N; i++) {
            sequence[depth] = numbers[i]; // 현재 숫자를 수열에 추가
            backtrack(numbers, sequence, depth + 1, i, N, M, resultSet); // 재귀 호출
        }
    }
}
