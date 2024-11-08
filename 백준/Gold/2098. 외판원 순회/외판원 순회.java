import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] route;
    static int[][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        route = new int[N][N];
        dp = new int[1<<N][N]; //근데 왜 1<<N 크기만큼 dp 배열을 만드는거지?

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                route[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(travelling(1,0));    //비트마스킹 1 은 방문완료
    }


    static int travelling(int visited, int now){
        if(visited == (1<<N)-1){    //출발지점으로 다시 돌아옴 (1<<N)-1 을 왜함??
            if(route[now][0] > 0){
                return route[now][0];
            }
            return INF;
        }

        if(dp[visited][now] > 0){   //값이 0 이상인 경우 메모리제이션 완료
            return dp[visited][now];
        }
        dp[visited][now] = INF; //해당 경로는 방문 전으로 최소값을 찾기위해 최대값으로 초기화

        for(int next=0; next < N; next++){
            if(stop(visited, now, next)){
                continue;
            }
            dp[visited][now] = Math.min(
                    dp[visited][now], travelling(visited |(1<<next),next) + route[now][next]
            );
        }

        return dp[visited][now];
    }

    //다음에 이동할 도시를 이미 순회했고, 도시 i에서 도시 j로 갈 수 없는 경우
    private static boolean stop (int visited, int now, int next){
        return (visited & (1<<next)) > 0 || route[now][next] == 0;
    }
}