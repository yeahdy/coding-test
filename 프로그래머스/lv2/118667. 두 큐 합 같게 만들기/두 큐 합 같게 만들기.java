import java.util.*;

/* point!
while의 조건은 break 의 조건으로 걸기
두개의 대상을 비교(수의 크기x)할 경우 둘 중 하나를 기준으로 잡고 대소비교하기
-1 을 리턴할 조건 찾기

1.각 큐의 합을 통해 목표값을 구한다.
2.큰쪽에서 작은쪽으로 이동한다.
3.이동했을 경우 answer +1
4.목표값이 됐는지 확인

골칫덩이!
case11: [101, 100], [102, 103]

*/
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sumQueue1 = 0, sumQueue2 = 0, total = 0;

        for(int i=0; i < queue1.length; i++){
            sumQueue1 += queue1[i];
            q1.offer(queue1[i]);
            
            sumQueue2 += queue2[i];
            q2.offer(queue2[i]);
        }
        
        total= sumQueue1+sumQueue2;
        if(total % 2 != 0) return -1;
        long goal = total/2;
        
        while(goal != sumQueue1){
            if(answer > (queue1.length+queue1.length)+1) return -1;
            
            if(sumQueue1 > goal){
                sumQueue1 -= q1.peek();
                q2.offer(q1.poll());
            }else{
                sumQueue1 += q2.peek();
                q1.offer(q2.poll());
            }
            answer++;
        }
        return answer;
    }
}
