import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());    //테스트케이스 수

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());    //가로
            N = Integer.parseInt(st.nextToken());    //세로
            int K = Integer.parseInt(st.nextToken());    //배추 개수

            int[][] map = new int[N][M];
            Queue<int[]> 배추위치 = new LinkedList<>();
            while(K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int X  = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                map[Y][X] = 1;
                배추위치.offer(new int[] {X, Y});
            }
            꼬물꼬물(map, 배추위치);
        }
        bw.flush();
        bw.close();
    }

    private static void 꼬물꼬물(int[][] map, Queue<int[]> 배추위치) throws IOException {
        int 지렁이 = 0;

        while(!배추위치.isEmpty()) {

            int[] 배추 = 배추위치.poll();
            int X = 배추[0];
            int Y = 배추[1];

            if(map[Y][X] != 2) {    //지렁이가 아직 방문하지 않은 배추라면
                지렁이++;  //그 위치에 지렁이를 한 마리 푼다
                BFS(map, 배추);   //그 위치에서부터 BFS로 지렁이가 움직임
            }
        }

        bw.write(지렁이 + "");
        bw.newLine();
    }

    private static void BFS(int[][] map, int[] 배추) {
        int[][] move = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};  //사방 탐색
        Queue<int[]> q = new LinkedList<>();
        q.offer(배추);

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int[] m : move) {
                int nX = cur[0] + m[0];
                int nY = cur[1] + m[1];

                if(nY >= 0 && nY < N && nX >= 0 && nX < M && map[nY][nX] == 1) {    //이동 범위와 방문 체크
                    map[nY][nX] = 2;
                    q.offer(new int[] {nX, nY});
                }
            }
        }
    }
}
