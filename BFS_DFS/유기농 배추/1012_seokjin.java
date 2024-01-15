import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 해당 지역에 배추를 모두 탐색하면 count를 하면 된다.
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 가로
            int n = Integer.parseInt(st.nextToken()); // 세로
            int k = Integer.parseInt(st.nextToken()); // 배추 갯수

            int[][] map = new int[m][n];

            // 배추 심기
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }
            Queue<Pair> qu = new LinkedList<>();
            int count = 0;
            // 4방 탐색
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            // 밭 전체 탐색
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    // 배추가 없는 곳은 바로 건너뛴다.
                    if (map[x][y] != 1) continue;
                    qu.offer(new Pair(x, y));

                    while (!qu.isEmpty()) {
                        Pair p = qu.poll();
                        // 현재 배추의 위치에서 4방 탐색 시작
                        for (int z = 0; z < 4; z++) {
                            int d_x = p.x + dx[z];
                            int d_y = p.y + dy[z];
                            // 밭의 크기에서 벗어난 좌표는 바로 건너 뛴다.
                            if (d_x < 0 || d_y < 0 || d_x >= m || d_y >= n) continue;
                                // 밭의 범위 내에서 배추가 심어져 있는 경우
                            else if (map[d_x][d_y] == 1) {
                                map[d_x][d_y] = 0; // 탐색을 했으니 방문 처리로 0으로 값을 바꿔준다 -> 다시 탐색을 하지 않기 위한 방법
                                qu.add(new Pair(d_x, d_y)); // 해당 좌표를 큐에 넣어준다.
                            }
                        }
                    }
                    // 큐가 비었다는 뜻은 주위에 더 이상 배추가 없다는 뜻이므로 벌레 하나가 필요하다.
                    count++;
                }
            }
            System.out.print(count + " ");
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
