import java.util.Stack;

class Solution {
    /*
    [1,2,3,4,5]     temp:  
    [1,2,3,4,5]     truck: 1 

    [해결책!]
    order의 크기가 끝까지 될때까지 반복을 돌린다.
    컨테이너에서 꺼내거나, temp에서 꺼낸게 order 순서이면 truck에 담는다.
    그렇지 않으면, temp에 담는다.
    */
    public int solution(int[] order) {
        Stack<Integer> temp = new Stack<>();
        int truck = 0, box = 1;

        for(int o : order){
            // 컨테이너 > 임시컨테이너 택배 나르기
            while(box <= order.length){
                if(box == o){
                    break;
                } else if (!temp.isEmpty() && temp.peek() == o) {
                    break;
                }else {
                    temp.push(box);
                    box++;
                }
            }

            if(box == o){   // 컨테이너에서 가져오기
                box++;
                truck++;
            } else if (!temp.isEmpty() && temp.peek() == o) {   // 임시에서 가져오기
                temp.pop();
                truck++;
            } else{ // 둘다 아니면 끝
                break;
            }
        }
        return truck;
    }
}
