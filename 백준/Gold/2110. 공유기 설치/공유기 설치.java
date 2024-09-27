import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] data = new int[N];

        // 데이터 입력 받기
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine()); // 각 입력을 개별적으로 읽어들임
        }

        Arrays.sort(data); // 입력된 데이터를 정렬

        int start = 1;
        int end = data[N - 1] - data[0]; // 최대 거리 계산
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int count = 1;
            int prev = data[0];

            // 조건에 맞는 전방 위치 카운트
            for (int i = 1; i < N; i++) {
                if (data[i] - prev >= mid) {
                    count++;
                    prev = data[i];
                }
            }

            // 카운트가 C보다 작으면 거리를 줄임
            if (count < C) {
                end = mid - 1;
            } else {
                result = mid; // 조건을 만족하는 최대 거리
                start = mid + 1;
            }
        }

        System.out.println(result); // 최종 결과 출력
    }
}
