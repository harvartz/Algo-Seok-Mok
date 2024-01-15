import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] dx = {-1, -2, 1, 2, -2, -1, 2, 1};
        int[] dy = {2, 1, 2, 1, -1, -2, -1, -2};

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            // 배열의 인덱스로 가려면 얼만큼 걸리는 지 확인할 수 있는 배열
            int[][] dist = new int[N][N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            Queue<Pair> qu = new LinkedList<>();
            qu.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

            // 목표 위치
            st = new StringTokenizer(br.readLine());
            int target_x = Integer.parseInt(st.nextToken());
            int target_y = Integer.parseInt(st.nextToken());

            int count = 0;
            while (!qu.isEmpty()) {
                Pair p = qu.poll();
                // 타겟에 도달할 경우, 해당 dist 배열을 answer에 대입
                if (p.x == target_x && p.y == target_y) {
                    count = dist[p.x][p.y];
                    break;
                }
                // 8방 탐색
                for (int i = 0; i < 8; i++) {
                    int d_x = p.x + dx[i];
                    int d_y = p.y + dy[i];

                    // 크기에서 벗어나는 경우
                    if (d_x < 0 || d_y < 0 || d_x >= N || d_y >= N) continue;
                    // 이미 한번 방문한 경우
                    if (dist[d_x][d_y] > 0) continue;
                    dist[d_x][d_y] = dist[p.x][p.y] + 1;
                    qu.offer(new Pair(d_x, d_y));
                }
            }
            System.out.println(count);
        }
    }
}

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
