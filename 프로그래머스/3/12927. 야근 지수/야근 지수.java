import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        //내림차순 정렬
        for(int w : works){
            queue.offer(w);
        }

        while(!queue.isEmpty() && n>0){
            int current = queue.poll();
            if(current > 0){
                current = current-1;
                n--;
                if(current == 0){
                    continue;
                }
                queue.offer(current);
            }
        }

        while(!queue.isEmpty()){
            answer += (long) Math.pow(queue.poll(),2);
        }
        return answer;  //야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값
    }

}