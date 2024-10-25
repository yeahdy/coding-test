import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split("\\s");
        String[] h = br.readLine().split("\\s");
        int[] exhaustion = new int[N+1];
        int[] happy = new int[N+1];

        for(int i=1; i<N+1; i++){
            exhaustion[i] = Integer.parseInt(s[i-1]);
            happy[i] = Integer.parseInt(h[i-1]);
        }

        final int MAX_STAMINA = 100;
        int[] dp = new int[MAX_STAMINA+1];   //dp[체력]= 최대 기쁨
        for(int i = 1; i < N+1; i++){
            for(int stamina=MAX_STAMINA-1; stamina >= 0; stamina--) {   //뒤에서 부터 특정 체력에 대한 기쁨을 갱신하면서 반복
                int total = exhaustion[i]+stamina;  //현재 체력과 체력소모를 더해서 현재 상태에서 가능한 상황을 고려
                if (total < MAX_STAMINA) {
                    dp[total] = Math.max(dp[total], dp[stamina] + happy[i]);    //"이미 갱신된 기쁨"과 "현재 체력에 대한 기쁨"을 비교하기
                    //System.out.printf("dp[%d] = Math.max(dp[%d],dp[%d]+happyArr[%d])\n", total, total, stamina , i);
                    //System.out.printf("dp[%d] = Math.max(%d,%d)\n\n", total, dp[total],dp[stamina] + happy[i]);
                }
            }
        }

        System.out.println(dp[99]);
    }
}