import java.util.*;
//dp
//모든 알고력과 코딩력을 만족하는 문제를 "최소시간"에 풀기
//최대 알고력과 최대 코딩력 만큼 시간 들이기
class Solution {
    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};
        System.out.println(solution(alp,cop,problems));
    }

    static class Problem {
        int alpReq;
        int copReq;
        int alpRwd;
        int copRwd;
        int cost;

        Problem (int alpReq, int copReq, int alpRwd, int copRwd, int cost){
            this.alpReq = alpReq;
            this.copReq = copReq;
            this.alpRwd = alpRwd;
            this.copRwd = copRwd;
            this.cost = cost;
        }
    }

    static final int MAX = 300;
    static int[][] dp;
    static List<Problem> list = new ArrayList<>();
    static public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        for(int[] problem : problems){
            list.add(new Problem(problem[0],problem[1],problem[2],problem[3],problem[4]));
            maxAlp = Math.max(maxAlp,problem[0]);
            maxCop = Math.max(maxCop,problem[1]);
        }
        //알고력과 코딩력이 충분할 경우
        if(maxAlp <= alp && maxCop <= cop){
            return 0;
        }
        //★목표치까지만 dp점화식을 구할거기 때문에 현재 알고력과 코딩력 보정하기
        if(maxAlp <= alp){
            alp = maxAlp;
        }else if(maxCop <= cop){
            cop = maxCop;
        }

        //특정 알고력과 특정 코딩력일 때 최소시간
        dp = new int[maxAlp+2][maxCop+2]; //dp[알고력][코딩력] = 최소시간
        for(int[] d : dp){
            Arrays.fill(d,MAX);
        }
        dp[alp][cop] = 0;   //★현재 상태부터 시작

        calculateDp(alp, cop, maxAlp, maxCop);
        return dp[maxAlp][maxCop];
    }


    static void calculateDp(int alp, int cop, int maxAlp, int maxCop){
        //현재 코딩력과 알고력을 시작으로 모든 문제를 풀 수 있는 최소 시간
        for(int i=alp; i<=maxAlp; i++){
            for(int j=cop; j<=maxCop; j++){
                //System.out.printf("dp[%d][%d] = %d%n",i,j,dp[i][j]);
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);  //알고력 학습
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);  //코딩력 학습

                //★문제풀기: 문제를 풀었을 때 코딩력과 알고력에 대한 최소시간 구하기
                for(Problem problem: list){
                    int sovledAlp = i+problem.alpRwd;
                    int solvedRwd = j+ problem.copRwd;

                    if(problem.alpReq > i || problem.copReq > j){   //현재 풀수 없는 문제
                        continue;
                    }

                    //알고력과 코딩력이 목표치에 도달했을 경우 최소 시간 찾기
                    if(sovledAlp >= maxAlp && solvedRwd >= maxCop){
                        dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[i][j]+problem.cost);
                    }
                    else if(sovledAlp >= maxAlp){
                        dp[maxAlp][solvedRwd] = Math.min(dp[maxAlp][solvedRwd], dp[i][j]+problem.cost);
                    }
                    else if(solvedRwd >= maxCop){
                        dp[sovledAlp][maxCop] = Math.min(dp[sovledAlp][maxCop], dp[i][j]+problem.cost);
                    }
                    else{
                        dp[sovledAlp][solvedRwd] = Math.min(dp[sovledAlp][solvedRwd], dp[i][j]+problem.cost);
                    }
                }//problem
            }//j
        }//i
    }
}