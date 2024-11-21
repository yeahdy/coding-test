import java.util.*;

//그리디
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<enemy.length; i++){
            if(n < enemy[i] && k == 0){
                break;
            }
            
            pq.offer(enemy[i]); //적 추가 
            //System.out.printf("%d < enemy[%d] (%d)%n",n,i,enemy[i]);
            //System.out.println(pq);
            if(n < enemy[i]){   //현재 병사로 적 제거가 불가능할 경우
                n += pq.poll();
                k--;
            }
            n -= enemy[i];  //적 제거    
            answer++;
        }
        
        return answer;
    }
}