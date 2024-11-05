//stones길이  1 이상 200,000 이하, 니니즈가 무제한이므로 완전탐색 시 시간초과
//매개변수 이진탐색은 정렬을 하지 않아도 됨
//핵심은 "최대 친구들"이 다리를 건널 수 있는지, 없는지를 판단하는 것
class Solution {
    public int solution(int[] stones, int k) {
        int friends = 0; 
        
        int left = 1;
        int right = 200_000_000;    //★친구들의 수를 최대값으로 지정
        while(left <= right){
            int mid = (left + right)/2;
        
            //더 많은 친구들이 다리를 건널 수 있는지
            if(canGo(mid,stones,k)){
                left = mid+1;
                friends = Math.max(friends, mid);
            }else{
                //친구들이 다리를 건널 수 없다면 숫자를 줄이기
                right = mid-1;
            }
        }
        
        return friends;
    }

    boolean canGo(int friends, int[] stones, int k){
        int count = 0;
        for(int stone : stones){
            //한명씩 비교할 필요XXXXX ★하나의 돌다리에 친구 수만큼 올라갈 수 있는지만 판단★
            if(stone - friends < 0){
                count++;
                if(count == k){
                    return false;
                }
                continue;
            }
            count = 0;
        }
        
        return true;
    }
}