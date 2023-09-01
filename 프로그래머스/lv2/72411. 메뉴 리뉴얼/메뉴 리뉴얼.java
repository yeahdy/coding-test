import java.util.*;
import java.util.stream.*;

class Solution {
    Map<String,Integer> orderMap = new HashMap<>();
    List<String> courseOrders = new ArrayList<>();
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        //※가장 많이 뽑힌 코스요리를 선택하기 때문에 orders 배열의 정렬은 필요없음
        //주문 알파벳순 정렬
        int i=0;
        for(String order : orders){
            orders[i] = Stream.of(order.split(""))
                .sorted()
                .collect(Collectors.joining());
            i++;
        }
        
        //모든 주문에서 하나의 코스요리 갯수에 대한 조합을 뽑는다.
        for(int c : course){
            for(String order : orders){
                if(order.length() < c) continue;
                String orderCase = "";
                combination(order.toCharArray(), orderCase, 0, c);
            }
            //각 코스요리 갯수에서 가장 많이 뽑힌 요리 찾기!
            //Map의 value중 max를 찾은 후, max value인 것의 key를 찾아서 List에 넣는다.
            if(!orderMap.isEmpty()){
                Integer MAX = Collections.max(orderMap.values());
                for(Map.Entry<String, Integer> entry : orderMap.entrySet()){
                    if(MAX > 1 && entry.getValue() == MAX){
                        courseOrders.add(entry.getKey());
                    }
                }
                orderMap.clear();
            }
        }
        
        //사전 순으로 오름차순 정렬 + 원소 알파벳 오름차순 정렬
        Collections.sort(courseOrders);
        return courseOrders.stream().toArray(String[]::new);
    }
    
    //orderStr:뽑을 배열, orderCase:뽑은 문자열, idx:다음 시작점, r:뽑을 갯수
    private void combination(char[] orderStr, String orderCase, int idx, int r){
		if (r == 0) {
            //System.out.println("case :: " + orderCase);
            orderMap.put(orderCase, orderMap.getOrDefault(orderCase,0)+1);  //get 후 없을 경우 default값
            return;
		}
        
		for (int i = idx; i < orderStr.length; i++) {
			combination(orderStr, orderCase + orderStr[i], i + 1, r - 1);
            // System.out.println("다음 시작점: " + i + "다음 시작할 문자열: "+orderCase);
		}
    }
}