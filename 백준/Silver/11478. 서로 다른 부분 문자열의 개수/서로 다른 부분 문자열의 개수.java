import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위해 BufferedReader를 생성합니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 문자열 S를 입력받습니다.
        String str = br.readLine();
        // UniqueSubstringCounter 객체를 생성하여 서로 다른 부분 문자열의 개수를 계산합니다.
        UniqueSubstringCounter counter = new UniqueSubstringCounter();
        // 결과를 출력합니다.
        System.out.println(counter.countUniqueSubstrings(str));
    }
}

class UniqueSubstringCounter {
    // 주어진 문자열에서 서로 다른 부분 문자열의 개수를 계산하는 메서드입니다.
    public int countUniqueSubstrings(String str) {
        Set<String> uniqueSubstrings = new HashSet<>(); // 중복을 피하기 위해 HashSet을 사용합니다.
        int n = str.length(); // 문자열의 길이를 구합니다.

        // 부분 문자열을 생성하여 HashSet에 추가합니다.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                uniqueSubstrings.add(str.substring(i, j)); // HashSet에 부분 문자열을 추가합니다.
            }
        }

        return uniqueSubstrings.size(); // 서로 다른 부분 문자열의 개수를 반환합니다.
    }
}
