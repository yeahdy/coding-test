import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().getFuelPrice());
    }

    int getFuelPrice () throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] earthAndMoon = br.readLine().split("\\s");
        int N = Integer.parseInt(earthAndMoon[0]);
        int M = Integer.parseInt(earthAndMoon[1]);
        int[][] space = new int[N][M];

        //공간의 연료값 초기화
        for(int i=0; i<N; i++){
            String[] data = br.readLine().split("\\s");
            for(int j=0; j<M; j++){
                space[i][j] = Integer.parseInt(data[j]);
            }
        }

        //NOTE 전에 움직인 방향으로 이동 불가 어떻게 판단하지?
        int[][][] steps = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(steps[i][j], Integer.MAX_VALUE);
            }
        }

        //첫번째 행 초기화
        for(int j=0; j<M; j++){
            steps[0][j][0] = space[0][j];
            steps[0][j][1] = space[0][j];
            steps[0][j][2] = space[0][j];
        }

        for(int i=1; i<N; i++){ //다음행의 연료값 계산
            for(int j=0; j<M; j++){
                if(j == 0){
                    steps[i][j][0] = Math.min(steps[i-1][j+1][1],steps[i-1][j+1][2]) +space[i][j];
                    steps[i][j][1] = steps[i-1][j][0]+space[i][j];
                }else if(j == M-1){
                    steps[i][j][2] = Math.min(steps[i-1][j-1][1],steps[i-1][j-1][0]) +space[i][j];
                    steps[i][j][1] = steps[i-1][j][2]+space[i][j];
                } else{
                    steps[i][j][0] = Math.min(steps[i-1][j+1][1],steps[i-1][j+1][2]) +space[i][j];
                    steps[i][j][1] = Math.min(steps[i-1][j][0],steps[i-1][j][2]) +space[i][j];
                    steps[i][j][2] = Math.min(steps[i-1][j-1][1],steps[i-1][j-1][0]) +space[i][j];
                }

            }
        }

        //마지막행의 최소값 찾기
        int minPrice = Integer.MAX_VALUE;
        for(int j=0; j<M; j++){
            for(int k=0; k<3; k++){
                minPrice = Math.min(minPrice,steps[N-1][j][k]);
            }
        }
        return minPrice;
    }

}
