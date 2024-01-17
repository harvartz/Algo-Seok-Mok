import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static int[] arr;
    public static boolean[] visit;
    static int N;
    static int M;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visit = new boolean[N];
        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int depth) {
        if (depth == M) {
            for (int x : arr) {
                sb.append(x).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int x = 1; x <= N; x++) {
            if (!visit[x - 1]) {
                // 깊이와 배열의 인덱스는 동일하다 => 배열의 마지막 인덱스는
                // 즉, 제한된 깊이와 동일 -> 탈출
                arr[depth] = x;
                visit[x - 1] = true;
                dfs(depth + 1);
                visit[x - 1] = false;
            }
        }
    }
}