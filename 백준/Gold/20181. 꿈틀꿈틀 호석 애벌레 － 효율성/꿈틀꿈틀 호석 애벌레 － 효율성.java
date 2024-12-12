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
        int satisfaction =branch[0];
        long[] dp = new long[n+1];  // dp[현재지점] = 최대 누적 탈피 에너지

        while(start < n+1){
            if(satisfaction < k){
                dp[start] = Math.max(dp[start],dp[start-1]);    //현재까지의 최대 누적에너지 갱신
                if(start < n+1){
                    satisfaction += branch[start++];    //k 보다 작으면 start 오른쪽으로 이동
                }
            }else{
                while(satisfaction >= k){
                    dp[start] = Math.max(dp[start], dp[end] + satisfaction - k);
                    satisfaction -= branch[end++];  //end 오른쪽으로 이동 시켜서 범위 좁히기
                }
            }
        }
        System.out.println(dp[n]);
    }
}