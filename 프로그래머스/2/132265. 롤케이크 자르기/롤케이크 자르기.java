import java.util.*;

//시간복잡도 O(n)
//나누는 기준을 어떻게 잡지? 
class Solution {
    public int solution(int[] topping) {
        int n = topping.length;
        HashSet<Integer> toppingSet = new HashSet<>();  //HashSet<자른구간,토핑갯수> 토핑 중복제거
        
        int[] left = new int[n];
        for(int i=0; i<n; i++){
            toppingSet.add(topping[i]);
            left[i] = toppingSet.size();
        }

        toppingSet.clear();
        int[] right = new int[n];
        for(int i=left.length-1; i>=0; i--){
            toppingSet.add(topping[i]);
            right[i] = toppingSet.size();
        }

        //공평하게 나눴는지 체크 > 공평하게 나눴다면 카운팅
        int answer = 0;
        for(int i=0; i<n-1; i++){
            if(left[i] == right[i+1]){
                answer++;
            }
        }

        return answer;
    }
}