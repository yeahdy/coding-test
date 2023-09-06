import java.util.*;
import java.util.stream.*;

class Solution {
    //dfs 풀이
    HashMap<String,Integer> orderMap = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        List<String> orderCourse = new ArrayList<>();
        
        //문자열 원소 오름차순 정렬
        for(int i=0; i<orders.length; i++){
            orders[i]= Arrays
                .stream(orders[i].split(""))
                .sorted()
                .collect(Collectors.joining());
        }
        
        //하나의 코스에 대한 모든 주문서를 뽑는다.
        for(int cs : course){
            for(String od : orders){
                if(od.length() < cs) continue;
                combination(od, "", 0, cs);
            }
            
            //각 코스의 최다 주문구성을 뽑는다
            if(!orderMap.isEmpty()){
                int MAX= Collections.max(orderMap.values());
                for(String odKey : orderMap.keySet()){
                    if(orderMap.get(odKey).equals(MAX) && MAX > 1){
                        orderCourse.add(odKey);
                    }
                }
                orderMap.clear();
            }
        }
        
        Collections.sort(orderCourse);
        return orderCourse.stream().toArray(String[]::new);
    }
    
    private void combination(String order, String selectedOd, int index, int course){
        if(course == 0){
            //System.out.printf("\t단어조합: %s\n",selectedOd);
            orderMap.put(selectedOd, orderMap.getOrDefault(selectedOd,0)+1);
            return;
        }
        
        // 한번 for문을 반복할 때, 재귀 호출을 통해 for문의 조건까지 계속해서 i+1 을 한다.
            // 따라서 'ABCFG'에서 2개를 뽑을 때
            // 첫 반복으로 A를 뽑아 놓고, A와 함께 조합될 나머지 한개를 재귀 호출을 통해 계속 i+1을 하며 뽑는다.
        for(int i=index; i<order.length(); i++){
            //System.out.printf("i: %d, index: %d, course: %d, char: %s\n",i,index,course,order.charAt(i));
            combination(order, selectedOd + order.charAt(i), i+1, course-1);            
        }
    }
    
}



