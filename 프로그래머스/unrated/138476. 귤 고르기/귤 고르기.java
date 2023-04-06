import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    // tangerine 에서의 종류별 각 중복 갯수    >> Collectors.toMap() 사용
    // k 개를 충족하는 종류의 수 
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Integer[] arr =  Arrays.stream(tangerine).boxed().toArray(Integer[]::new);
        Stream<Integer> intStr = Stream.of(arr);
        // 종류별 중복갯수 계산 <크기 종류, 중복 갯수>
        Map<Integer, Integer> tanMap = intStr.collect(
        		Collectors.toMap(Function.identity(), value -> 1, Integer::sum)
        		);
        
        // 내림차순 정렬
        List<Integer> keySet = new ArrayList<>(tanMap.keySet());
        keySet.sort((o1,o2) -> tanMap.get(o2).compareTo(tanMap.get(o1)));

        // Point. k 개 만큼 중복된 수를 누적해서 뺄 때 k의 수를 딱 맞추지 않아도 된다.
        int i= 0;
        while(true) {
        	int duplicatedTanger = tanMap.get(keySet.get(i));
            
            k -= duplicatedTanger;
            answer++;
            if(k <= 0) break;
            i++;            
        }
        
        return answer;
    }
}