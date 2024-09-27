import java.util.*;

public class Main {
    static class Knight{
        int x;
        int y;
        int distance;
        Knight(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static Queue<Knight> queue = new LinkedList<>();
    static int[][] MOVEMENTS = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};
    static void findMinMoveCount(int[][] chessBoard, boolean[][] visited) {
        while(!queue.isEmpty()){
            Knight knight = queue.poll();

            for(int[] move : MOVEMENTS){
                int nextX = knight.x + move[0];
                int nextY = knight.y + move[1];

                //이동할 위치에 나이트가 갈 수 없다면 해당 구간 탐색 x
                if(nextX<1 || nextX >= chessBoard.length || nextY<1 || nextY >= chessBoard.length || visited[nextX][nextY]){
                    continue;
                }

                visited[nextX][nextY] = true;
                //다음 위치로 갈 나이트에게 현재 위치~다음 위치까지의 이동거리 누적 저장
                int distance = knight.distance + 1;
                queue.offer(new Knight(nextX, nextY, distance));
                chessBoard[nextX][nextY] = distance;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);;
        //NxN:체스판, M:말 갯수
        String[] first = sc.nextLine().split(" ");
        int N = Integer.parseInt(first[0]);
        int M = Integer.parseInt(first[1]);

        int[][] chessBoard = new int[N+1][N+1];
        boolean[][] visited = new boolean[N+1][N+1];

        //나이트위치
        String[] second = sc.nextLine().split(" ");
        int x =Integer.parseInt(second[0]);
        int y =Integer.parseInt(second[1]);
        queue.offer(new Knight(x,y,0));
        visited[x][y] = true;

        //말들 위치 저장
        List<int[]> enemys = new ArrayList<>();
        for(int i=0; i<M; i++){
            String[] line = sc.nextLine().split(" ");
            int enemyX = Integer.parseInt(line[0]);
            int enemyY = Integer.parseInt(line[1]);
            enemys.add(new int[]{enemyX,enemyY});
        }

        findMinMoveCount(chessBoard, visited);
        for(int[] e : enemys){
            System.out.print(chessBoard[e[0]][e[1]] + " ");
        }
    }
}