import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 섬의 개수
        M = Integer.parseInt(st.nextToken()); // 다리의 개수
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // 출발 섬
        E = Integer.parseInt(st.nextToken()); // 도착 섬
        
        // 인접 리스트 초기화
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            edges[h1].add(new Edge(h2, k));
            edges[h2].add(new Edge(h1, k));
        }

        int left = 1;
        int right = 1_000_000;
        int answer = 0;

        // 이분 탐색
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    // BFS를 이용한 탐색 함수
    static boolean bfs(int weightLimit) {
        boolean[] visit = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(S);
        visit[S] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == E) {
                return true;
            }

            for (Edge edge : edges[now]) {
                if (!visit[edge.to] && edge.weight >= weightLimit) {
                    queue.add(edge.to);
                    visit[edge.to] = true;
                }
            }
        }
        return false;
    }

    // 간선 클래스 정의
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
