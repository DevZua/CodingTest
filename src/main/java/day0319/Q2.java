package day0319;

// 문자열 my_string 과 문자 letter 이 매개변수로 주어집니다.
// my_string 에서 letter 를 제거한 문자열을 return 하도록
// solution 함수를 완성해주세요.

// 1 ≤ my_string 의 길이 ≤ 100
// letter 은 길이가 1인 영문자입니다.
// my_string 과 letter 은 알파벳 대소문자로 이루어져 있습니다.
// 대문자와 소문자를 구분합니다.

public class Q2 {
    public String solution(String my_string, String letter) {
        // 주어진 문자열에서 주어진 문자를 제거하기 위해 replace 메서드 활용
        String answer = my_string.replace(letter, "");
        return answer;
    }
}
