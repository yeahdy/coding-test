import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        
        HashSet<String> set = new HashSet<>();
        List<Character> list = new ArrayList<>();
        int order= 0;
        
        for(String word : words){
            order++;
            // 끝말잇기 확인
            if(list.size() > 0){
                if(list.get(list.size()-1) != word.charAt(0)){
                    // System.out.printf("마지막 list: %c / 단어 첫글자: %c%n",list.get(list.size()-1),word.charAt(0));
                    answer[0] = order;
                    answer[1] = ((set.size()+1)%n) == 0?((set.size()+1)/n) : ((set.size()+1)/n)+1;
                    break;
                }
            }
            
            // 중복확인
            if(set.contains(word)){
                answer[0] = order;
                answer[1] = ((set.size()+1)%n) == 0?((set.size()+1)/n) : ((set.size()+1)/n)+1;
                System.out.printf("set 길이: %d%n",set.size());
                break;
            }else{
                set.add(word);
                // ["hello", "observe", "effect"] > list: ['o','e','t']
                list.add(word.charAt(word.length()-1)); // 단어의 끝의 문자열 넣기
            }
            if(order == n) order = 0; 
        }
        
        return answer;
    }

    
}