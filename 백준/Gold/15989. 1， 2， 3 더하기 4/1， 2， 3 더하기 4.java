import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] testCase = new int[len];
        for(int i=0; i<len; i++){
            testCase[i]= sc.nextInt();
        }

        int[][] dp = new int[10001][3];
        //1
        dp[0][0] = 1;
        //2
        dp[1][0] = 1;
        dp[1][1] = 1;
        //3
        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;

        for(int i=3; i<dp.length; i++){
            dp[i][0] = dp[i-1][0];
            dp[i][1] = dp[i-2][0] + dp[i-2][1];
            dp[i][2] = dp[i-3][0] + dp[i-3][1] + dp[i-3][2];
        }

        for(int test : testCase){
            test = test-1;
            System.out.println(dp[test][0] + dp[test][1] + dp[test][2]);
        }
    }
}