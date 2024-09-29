import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        
        //중간값을 어떤걸로 정할것인가
        //어떤 기준에서 left 또는 right를 움직일 것인가?
        int left= 0;
        int right=distance;
        while(left<=right){
            int mid = (left+right)/2;
        
            int remove =0;
            int current=0;
            for(int i=0; i<rocks.length; i++){
                //다음 바위-현재바위가 현재 최소구간보다 작을 경우 최소값 중 큰값 후보에서 제외
                //제외 == 제거해야 하는 바위
                if(mid > rocks[i]-current){
                    remove++;
                    continue;
                }
                current = rocks[i];
            }
            
            //도착구간-마지막 바위
            if(mid > distance - current){
                remove++;
            }
            
            if(remove <= n){
                answer= Math.max(answer,mid);
                left = mid+1;   //더 큰값을 탐색해서 최소거리 최대화
            }else{
                right = mid-1;  
            }
        }
        
        return answer;
    }
    
}