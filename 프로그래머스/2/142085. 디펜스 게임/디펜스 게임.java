import java.util.*;

//그리디
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int e : enemy) {
            if (n < e && k == 0) {
                break;
            }

            pq.offer(e);
            if (n < e) {
                n += pq.poll();
                k--;
            }
            n -= e;
            answer++;
        }

        return answer;
    }
}