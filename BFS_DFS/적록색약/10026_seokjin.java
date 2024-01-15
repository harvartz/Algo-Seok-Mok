import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];
        boolean[][] visited = new boolean[n][n];
        Queue<Pair> qu = new LinkedList<>();

        for (int x = 0; x < n; x++) {
            String input = br.readLine();
            for (int y = 0; y < n; y++) {
                map[x][y] = input.charAt(y);
            }
        }
        // 구하는 것은 적록색약이 아닐 때, 적록 색약일 때의 구역의 갯수
        int count = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (visited[x][y] == true) continue;
                qu.offer(new Pair(x, y));
                while (!qu.isEmpty()) {
                    Pair p = qu.poll();
                    visited[p.x][p.y] = true;
                    for (int z = 0; z < 4; z++) {
                        int d_x = p.x + dx[z];
                        int d_y = p.y + dy[z];

                        if (d_x < 0 || d_y < 0 || d_x >= n || d_y >= n) continue;
                        // 적록 색약이 아닌 경우
                        if (map[d_x][d_y] == map[p.x][p.y] && !visited[d_x][d_y]) {
                            visited[d_x][d_y] = true;
                            qu.offer(new Pair(d_x, d_y));
                        }
                        // 적록 색약인 경우
                    }
                }
                count++;
            }
        }
        sb.append(count + " ");
        count = 0;

        // 적록 색약인 경우
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (map[x][y] == 'R') map[x][y] = 'G';
            }
        }

        visited = new boolean[n][n];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (visited[x][y] == true) continue;
                qu.offer(new Pair(x, y));
                while (!qu.isEmpty()) {
                    Pair p = qu.poll();
                    visited[p.x][p.y] = true;
                    for (int z = 0; z < 4; z++) {
                        int d_x = p.x + dx[z];
                        int d_y = p.y + dy[z];

                        if (d_x < 0 || d_y < 0 || d_x >= n || d_y >= n) continue;
                        // 적록 색약이 아닌 경우
                        if (map[d_x][d_y] == map[p.x][p.y] && !visited[d_x][d_y]) {
                            visited[d_x][d_y] = true;
                            qu.offer(new Pair(d_x, d_y));
                        }
                        // 적록 색약인 경우
                    }
                }
                count++;
            }
        }
        sb.append(count);
        System.out.println(sb);
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
