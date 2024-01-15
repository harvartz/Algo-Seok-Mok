import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] address;
    static boolean[][] visited;
    static int n;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        address = new int[n][n];
        visited = new boolean[n][n];
        ArrayList<Integer> answer = new ArrayList<>();

        for (int x = 0; x < n; x++) {
            String input = br.readLine();
            for (int y = 0; y < n; y++) {
                address[x][y] = input.charAt(y) - '0';
            }
        }

        Queue<Pair> qu = new LinkedList<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                // 집이 없는 경우이거나 방문한 경우는 건너 뛴다.
                if (address[x][y] == 0 || visited[x][y] == true) continue;
                // 집이 있고 방문하지 않는 경우만 Queue에 집어 넣는다.
                qu.offer(new Pair(x, y));

                // ***** 처음에 방문처리를 하지 않아서 하나씩 더해지는 예외가 발생했다. *****
                visited[x][y] = true;

                // count는 0부터가 아니라 1부터
                int count = 1;

                while (!qu.isEmpty()) {
                    Pair p = qu.poll();
                    // 4방 탐색 시작
                    for (int z = 0; z < 4; z++) {
                        int d_x = p.x + dx[z];
                        int d_y = p.y + dy[z];

                        // 배열에서 벗어나면 반복문 넘어가기
                        if (d_x < 0 || d_x >= n || d_y < 0 || d_y >= n) continue;

                        // 집이 있고 방문하지 않는 집인 경우
                        if (address[d_x][d_y] == 1 && !visited[d_x][d_y]) {
                            visited[d_x][d_y] = true;
                            qu.offer(new Pair(d_x, d_y));
                            count++;
                        }
                    }
                }
                answer.add(count);
            }
        }


        Collections.sort(answer);
        System.out.println(answer.size());
        for (int x : answer) {
            System.out.println(x);
        }
    }
}
