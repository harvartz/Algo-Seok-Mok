import java.util.*;
import java.io.*;

public class BOJ10026 {

    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        char[][] 일반 = new char[N][N];    //적록색약이 아닌 사람이 보는 그림
        char[][] 적록 = new char[N][N];    //적록색약인 사람이 보는 그림

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            일반[i] = line.toCharArray();

            line = line.replaceAll("R", "G");    //빨강을 초록으로 치환
            적록[i] = line.toCharArray();    //적록색약용 그림 만들기
        }


        bw.write(구역찾기(일반) + " " + 구역찾기(적록));
        bw.flush();
        bw.close();
    }

    private static int 구역찾기(char[][] 그림) {
        int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        int result = 0;

        boolean[][] visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {    //해당 픽셀을 방문한 적이 없으면
                    result++;   //구역의 수를 늘리고
                    visited[i][j] = true;
                    BFS(그림, i, j, visited); //해당 픽셀부터 구역 탐색
                }
            }
        }
        return result;
    }

    private static void BFS(char[][] 그림, int n, int m, boolean[][] visited) {
        int[][] move = {{-1, 0},{0, -1},{1, 0},{0, 1}};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {n, m});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            char pixel = 그림[y][x];
            for(int[] mov : move) {
                int nY = cur[0] + mov[0];
                int nX = cur[1] + mov[1];

                if(nY >= 0 && nY < N && nX >= 0 && nX < N && !visited[nY][nX] && 그림[nY][nX] == pixel) {
                    visited[nY][nX] = true;
                    q.offer(new int[] {nY, nX});
                }
            }
        }
    }
}
