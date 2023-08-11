import java.util.*;

class Solution {
    /* 
    * 10일간 원하는 물품과 갯수가 일치하는 날의 갯수 구하기
    한번 돌릴때 want에 있는것을 하나만 비교하면 안됨
    
    1. 구매할 물품의 갯수 만큼 List에 물품을 추가
    ["banana","banana","banana", "apple","apple", "rice","rice", "pork","pork", "pot"]
    
    2. discount를 10번 돌면서 List와 일치하는 물품이 있으면 List에서 제거한다.
    3. 10번을 다 돌은 후 List가 비어있으면 원하는 물품을 할인기간에 모두 구매 가능하므로 answer++
    4. 다음날로 넘어간 후 List를 다시 초기화 한 후 2.부터 다시 반복하기
    */
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        List<String> wantList = getWantList(want, number);
        List<String> tempWantList = new ArrayList<>(wantList);
        
        // discount 갯수-10번째 일까지만 물품의 갯수에 맞게 구매 가능함.
        int availableDays= discount.length-10, i= 0;
        while(i <= availableDays){
            for(int j=i; j<10+i; j++){
                String target = discount[j];
                if(tempWantList.contains(target)){
                    tempWantList.remove(target);
                }
            }
            
            if(tempWantList.isEmpty()){
                answer++;
            }
            tempWantList = new ArrayList<>(wantList);   // wantList 초기화
            i++;    //다음날로 넘기기
        }
        
        return answer;
    }
    
    private List<String> getWantList(String[] want, int[] number){
        List<String> wantList = new ArrayList<>();
        int i=0;
        for(int n : number){            
            for(int j=0; j<n; j++){
                wantList.add(want[i]);
            }
            i++;
        }
        return wantList;
    }
}