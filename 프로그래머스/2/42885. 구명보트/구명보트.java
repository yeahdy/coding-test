import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int boat = 0;

        Arrays.sort(people);
        int start = 0;
        for(int end=people.length-1; start<=end; end--){
            //기준값보다 작거나 같을 경우
            if(people[start] + people[end] <= limit){
                start++;
            }
            boat++;
        }
        return boat;
    }
}