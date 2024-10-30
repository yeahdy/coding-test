import java.util.*;
/**
알고리즘: 그리디
시간복잡도: 1 ≤ cap ≤ 50, 1 ≤ n ≤ 100,000
아이디어:
- 항상 cap 만큼 수거나 배달을 할건데, 선 배달 or 수거 / 후 cap 만큼 충전
- 배달 or 수거 할게 있다면 요청하기
- 배달 or 수거를 다 할때 까지 최대 용량 cap 만큼 더하기
   - 이때 현재 위치까지 왔다갔다는 뜻으로 현재위치*2 왕복 계산하기
   - 왔다간 이유는 한번 최대용량 만큼 충전하면 이후 남은 집들도 들리면서 배달 or 수거를 하기 때문

잘못생각한 이유:
마지막 집이 어딘지 찾는데 중점을 뒀기 때문에 많은 경우에 대해 하나씩 구현함..
(가장 마지막 집에 수거나 배달을 할 게 있다면 들려야하기 때문에)
*/
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int delivery = 0;
        int pickup = 0;
        long total = 0;
        
        for(int i =n-1; i>=0; i--){
            delivery += deliveries[i];
            pickup += pickups[i];
            //배달이나 수거할게 있을 경우
            while (delivery > 0 || pickup > 0){
                delivery -= cap;
                pickup -= cap;
                total += (i+1)*2;
            }
        }
        
        return total;
    }
}