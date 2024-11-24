import java.io.*;
import java.util.*;

public class Main {
    static int preIdx;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            
            // 입력 처리
            int[] preorder = new int[n];
            int[] inorder = new int[n];
            Map<Integer, Integer> inorderMap = new HashMap<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
                inorderMap.put(inorder[i], i);
            }
            
            // 후위 순회 실행
            preIdx = 0;
            postorder(preorder, 0, n - 1, inorderMap);
            sb.append('\n');
        }
        
        System.out.print(sb);
    }
    
    static void postorder(int[] preorder, int start, int end, Map<Integer, Integer> inorderMap) {
        if (start > end) return;
        
        int rootVal = preorder[preIdx++];
        int rootIdx = inorderMap.get(rootVal);
        
        postorder(preorder, start, rootIdx - 1, inorderMap);    // 왼쪽 서브트리
        postorder(preorder, rootIdx + 1, end, inorderMap);      // 오른쪽 서브트리
        sb.append(rootVal).append(' ');                         // 현재 노드
    }
}