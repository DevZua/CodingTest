import java.io.*;
import java.util.*;

public class Main {
    static int[] preorder; // 전위 순회 배열
    static int[] inorder;  // 중위 순회 배열
    static int[] postorder; // 후위 순회 배열 (출력은 필요 없으므로, 이 배열은 생략 가능)
    static int preorderIndex; // 전위 순회 인덱스
    static Map<Integer, Integer> inorderIndexMap; // 중위 순회 값 -> 인덱스 매핑

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 노드 개수
            preorder = new int[n];
            inorder = new int[n];
            inorderIndexMap = new HashMap<>();

            // 전위 순회 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            // 중위 순회 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
                inorderIndexMap.put(inorder[i], i); // 값 -> 인덱스 매핑 저장
            }

            preorderIndex = 0; // 전위 순회 시작 인덱스 초기화
            reconstructAndPostorder(0, n - 1, sb); // 트리 재구성 및 후위 순회
            sb.append("\n");
        }

        System.out.print(sb.toString()); // 결과 출력
    }

    // 트리 재구성 및 후위 순회 출력
    static void reconstructAndPostorder(int inorderStart, int inorderEnd, StringBuilder sb) {
        // 중위 순회 범위가 잘못된 경우 반환 (리프 노드 도달)
        if (inorderStart > inorderEnd) {
            return;
        }

        // 현재 서브트리의 루트 노드
        int rootValue = preorder[preorderIndex++];
        int rootIndexInInorder = inorderIndexMap.get(rootValue);

        // 왼쪽 서브트리 후위 순회
        reconstructAndPostorder(inorderStart, rootIndexInInorder - 1, sb);

        // 오른쪽 서브트리 후위 순회
        reconstructAndPostorder(rootIndexInInorder + 1, inorderEnd, sb);

        // 현재 노드 출력 (후위 순회)
        sb.append(rootValue).append(" ");
    }
}
