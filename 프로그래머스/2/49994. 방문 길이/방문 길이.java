import java.util.*;

class Solution {
    int answer = 0;
    int[][] direction = {{0,1},{0,-1},{-1,0},{1,0}}; //상,하,좌,우
    HashSet<String> visited = new HashSet<>();
    Queue<int[]> queue = new LinkedList<>();
    public int solution(String dirs) {
        String[] input = dirs.split("");
        int[] start = {5,5};
        int[] end = {11,11};

        //현재위치 방문처리
        queue.offer(start);
        for(String i : input){
            switch(i){
                case "U":
                    bfs(end,visited,direction[0]);
                    break;
                case "D":
                    bfs(end,visited,direction[1]);
                    break;
                case "L":
                    bfs(end,visited,direction[2]);
                    break;
                case "R":
                    bfs(end,visited,direction[3]);
                    break;
            }
        }
        return answer;
    }

    private void bfs(int[] end, HashSet<String> visited, int[] direction){
        int[] current = queue.peek();
        int x = current[0];
        int y = current[1];
        //위치 이동
        int nx =  x + direction[0];
        int ny = y + direction[1];
        String visitLength = ""+x+y+nx+ny;
        String reverseLength = ""+nx+ny+x+y;

        if(nx < 0 || nx > end[0]-1 || ny < 0 || ny > end[1]-1){
            return;
        }
        //방문처리
        queue.poll();

        if(visited.contains(visitLength) || visited.contains(reverseLength)){
            queue.offer(new int[]{nx,ny});
            return;
        }
        visited.add(visitLength);
        queue.offer(new int[]{nx,ny});
        answer++;
    }
}