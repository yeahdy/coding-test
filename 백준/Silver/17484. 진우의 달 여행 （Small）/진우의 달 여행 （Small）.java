import java.io.*;
import java.util.*;

public class Main{
    private static int[][] maps;
    private static int M, N;
    private static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < 3; j++) {//처음에 시작할때 3방향으로 갈 수 있음
                solve(0, i, maps[0][i], j);
            }
        }

        System.out.println(result);
    }

    static int[][] dir = {{1, -1}, {1, 0}, {1, 1}};

    static void solve(int nowX, int nowY, int sum, int d) {
        if(nowX == N-1) {//도착한 경우
            result = Math.min(result, sum);
            return;
        }

        for(int i = 0; i < 3; i++) {
            if(i == d) continue;//같은 방향으로 두번 연속 움직임 x
            int nextX = nowX + dir[i][0];
            int nextY = nowY + dir[i][1];

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;

            solve(nextX, nextY, sum + maps[nextX][nextY], i);
        }

    }

}