import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int s : scoville){
            queue.offer(s);
        }
        
        while(queue.size() > 1){
            int first = queue.poll();
            if(first >= K){
                break;
            }
            int second = queue.poll();
            int mixed = calculateScoville(first,second);
            queue.offer(mixed);
            answer++;
        }

        if(queue.poll() < K){
            return -1;
        } else {
            return answer;
        }
    }

    //첫번째로 낮은 스코빌 지수 + (두번째로 낮은 스코빌 지수 * 2)
    private int calculateScoville(int first, int second){
        return first + (second*2);
    }
}