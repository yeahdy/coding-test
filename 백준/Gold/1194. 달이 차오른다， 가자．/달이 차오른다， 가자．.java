import java.io.*;
import java.util.*;

public class Main {
    static class Pos {
        int x;
        int y;
        int distance;
        int hasKey;

        public Pos(int x, int y, int distance, int hasKey) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.hasKey = hasKey;
        }
    }

    static final char EXIT = '1';
    static final char CURRENT = '0';
    static final char WALL = '#';

    static int N;
    static int M;
    static char[][] maze;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        visited = new boolean[N][M][64];

        int x = 0;
        int y = 0;
        for(int i=0; i<N; i++) {
            String data = br.readLine();
            for(int j=0; j<M; j++) {
                maze[i][j] = data.charAt(j);
                if(maze[i][j] == CURRENT) {
                    x = i;
                    y = j;
                }
            }
        }

        System.out.println(escape(x,y));
    }

    static boolean[][][] visited;
    static int escape(int x, int y){
        Deque<Pos> pq = new ArrayDeque<>();
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        pq.offer(new Pos(x,y,0,0));
        visited[x][y][0] = true;

        while(!pq.isEmpty()) {
            Pos pos = pq.poll();
            if(maze[pos.x][pos.y] == EXIT) {
                return pos.distance;
            }

            for(int d=0; d<4; d++){
                int nx = pos.x + dx[d];
                int ny = pos.y + dy[d];
                if(stop(nx,ny, pos.hasKey)){
                    continue;
                }

                int key = pos.hasKey;
                if(maze[nx][ny] >= 'A' && maze[nx][ny] <= 'F') {
                    if((key & 1<<(maze[nx][ny] - 'A')) > 0){    //가지고 있는 키와 일치하는 경우
                        visited[nx][ny][key] = true;
                    }else{
                        continue;
                    }
                }else if(maze[nx][ny] >= 'a' && maze[nx][ny] <= 'f'){   //키를 주운 경우
                    key |= 1<<(maze[nx][ny] - 'a');
                    visited[nx][ny][key] = true;
                }else{
                    visited[nx][ny][key] = true;    //'.'
                }

                pq.offer(new Pos(nx,ny,pos.distance+1,key));
            }
        }

        return -1;
    }

    static private boolean stop(int x, int y, int hasKey){
        return x < 0 || x >= N || y < 0 || y >= M || maze[x][y] == WALL || visited[x][y][hasKey];
    }

}