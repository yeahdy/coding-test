import java.io.*;
import java.util.*;

//투포인터+dp : 1<=N<=100,000 > O(N)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] branch = new int[n+1];
        for(int i=0; i<n; i++){
            branch[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = 0;
        int satisfaction =0;
        long[] dp = new long[n+1];  // dp[현재지점] = 최대 누적 탈피 에너지

        while (start <= n) {
            satisfaction += branch[start];
            dp[start] = dp[start - 1];
            while (satisfaction >= k) {
                dp[start] = Math.max(dp[start], dp[end] + satisfaction - k);
                satisfaction -= branch[end + 1];
                end++;
            }
            start++;
        }

        System.out.println(dp[n]);
    }
}