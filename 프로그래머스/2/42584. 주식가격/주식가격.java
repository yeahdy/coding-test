import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        Queue<Integer> queue = new LinkedList<>();
        for(int price : prices){
            queue.offer(price);
        }
        
        int i=0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int k=i+1; k<len; k++){
                if(current > prices[k]){
                    answer[i]++;
                    break;
                }
                if(current <= prices[k]){
                    answer[i]++;
                }
            }
            i++;
        }

        return answer;
    }
}