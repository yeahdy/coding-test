import java.util.*;

class Solution {
    //뒷 큰수: 자신보다 크면서 가장 가까이 있는 수
    public int[] solution(int[] numbers) {
        int n = numbers.length;

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        stack.push(i);
        for(i=1; i<n; i++){
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){
               numbers[stack.pop()] = numbers[i];
            }
             stack.push(i);
        }
        
        while(!stack.isEmpty()){
            numbers[stack.pop()] = -1;
        }
        
        return numbers;
    }

}