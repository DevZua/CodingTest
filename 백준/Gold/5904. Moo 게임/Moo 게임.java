import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N번째 글자
        System.out.println(findMoo(N));
    }

    // Moo 수열에서 N번째 글자를 찾는 함수
    static char findMoo(int n) {
        int k = 0; // 현재 Moo 수열의 단계
        int length = 3; // Moo 수열의 초기 길이 S(0)

        // N이 포함된 수열의 길이를 구함
        while (n > length) {
            k++;
            length = 2 * length + (k + 3); // S(k)의 길이 = 2 * S(k-1) + (k + 3)
        }

        return findNthChar(n, k, length); // N번째 글자를 구하는 재귀 호출
    }

    // Moo 수열의 N번째 글자를 재귀적으로 찾는 함수
    static char findNthChar(int n, int k, int length) {
        int prevLength = (length - (k + 3)) / 2; // 이전 Moo 수열 S(k-1)의 길이

        if (n <= prevLength) { 
            // N번째 글자가 이전 S(k-1)에 있는 경우
            return findNthChar(n, k - 1, prevLength);
        } else if (n > prevLength + (k + 3)) { 
            // N번째 글자가 다음 S(k-1)에 있는 경우
            return findNthChar(n - prevLength - (k + 3), k - 1, prevLength);
        } else {
            // N번째 글자가 가운데 "moo...(k+3)개"에 있는 경우
            return (n - prevLength == 1) ? 'm' : 'o';
        }
    }
}
