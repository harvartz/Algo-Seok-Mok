import java.io.*;
import java.util.*;

public class Main {
    public static String[][] map;
    public static Queue<Pair> people;
    public static Queue<Pair> fire;

    public static int size_y, size_x;

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test = Integer.parseInt(bf.readLine());

        for (int t = 0; t < test; t++) {
            String[] str = bf.readLine().split(" ");

            size_y = Integer.parseInt(str[0]);
            size_x = Integer.parseInt(str[1]);
            // 맵 크기, 입력받을때 y의 크기가 먼저 나옴에 주의 할 것

            map = new String[size_x][size_y];

            people = new LinkedList<>();
            fire = new LinkedList<>();
            // 사람과 불의 위치

            for (int i = 0; i < size_x; i++) {
                str = bf.readLine().split("");

                for (int j = 0; j < size_y; j++) {
                    map[i][j] = str[j];

                    if (map[i][j].equals("@")) {
                        people.offer(new Pair(i, j, 0));
                    } // 사람 위치 저장

                    if (map[i][j].equals("*")) {
                        fire.offer(new Pair(i, j, 0));
                    } // 불의 위치 저장
                }
            }

            while (true) { // 반복이 종료될 때까지 계속
                if (people.size() == 0) {
                    bw.write("IMPOSSIBLE" + "\n");
                    break;
                }
                // 사람이 나갈 수 없거나, 불에 닿아서 큐에 저장된 좌표가 없음

                bfsFire();
                int cnt = bfsPeople();
                // 불 - 사람 순으로 bfs 탐색
                // 사람은 이동 칸 수를 반환받을 것
                // 나갈 수 있다면 이동 칸수를
                // 아직 나가지 못했으면 -1를 반환 받음

                if (cnt > 0) {
                    bw.write(cnt + "\n");
                    break;
                } // 나갈 수 있다면 이동 칸수를 출력
            }

        }

        bw.flush();
        bw.close();
    }

    public static int bfsPeople() {
        // 사람 이동
        int size = people.size();

        while (size-- > 0) {
            Pair p = people.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 사람을 네 방향으로 이동시켜보기
                if (check(nx, ny)) {
                    return p.dept + 1;
                } // 맵 바깥으로 나갈 수 있다면
                // 탈출을 할 수 있다는 뜻
                // 이동 칸수를 반환하고 종료

                if (map[nx][ny].equals("#") || map[nx][ny].equals("*")) continue;
                // 벽이거나 불은 갈 수 없음

                if (map[nx][ny].equals(".")) {
                    map[nx][ny] = "@";

                    people.offer(new Pair(nx, ny, p.dept + 1));
                }
                // 빈 공간은 이동할 수 있음
                // 이동 표시를 해주고, 해당 좌표와 이동 칸수 저장
            }
        }
        return -1;
        // 나갈 수 없거나, 아직 도달하지 못하면 -1 반환
    }

    public static void bfsFire() {
        // 불 확산
        int size = fire.size();
        // 현재 불의 개수만큼 불을 확산시킬 것

        while (size-- > 0) {
            Pair p = fire.poll();
            // 현재 불의 정보
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 네 방향으로 불을 확산
                if (check(nx, ny)) continue;
                // 맵 바깥은 패스
                if (map[nx][ny].equals("#")) continue;
                // 벽도 패스
                if (map[nx][ny].equals(".") || map[nx][ny].equals("@")) {
                    map[nx][ny] = "*";

                    fire.offer(new Pair(nx, ny, p.dept + 1));
                }
                // 빈 공간이거나, 사람이 있는 위치에는 확산 가능
                // 불 표시를 해주고, 좌표와 이동 칸수 저장
                // 불의 이동 칸수는 크게 필요없음
            }
        }
    }

    public static boolean check(int nx, int ny) {
        return nx < 0 || nx >= size_x || ny < 0 || ny >= size_y;
    } // 맵 바깥 체크

    public static class Pair {
        // 좌표와 이동 칸수
        int x;
        int y;
        int dept;

        public Pair(int x, int y, int dept) {
            this.x = x;
            this.y = y;
            this.dept = dept;

        }

    }

}