import java.util.Stack;

class Solution {
    // board: 인형뽑기, moves: 크레인의 이동, result: 2개연속 버스트 횟수
    // [[0,0,0,0,0],
    //  [0,0,1,0,3],
    //  [0,2,5,0,1],
    //  [4,2,4,4,2],
    //  [3,5,1,3,1]]            뽑기: [1,5,3,5,1,2,1,4]
    
    // 1: n행1열                 
    // 2: n행2열
    // 5: n행5열                
    // >> board에서 n행move열 가져와서 basket에 담기
    
    // 2개 연속일 경우 버스트 (Stack 사용)
    // >> 현재 값과 basket.peek()을 비교하여 같으면 pop() 하고, count++ 한다.
    // 그렇지 않으면 push()
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> basket = new Stack<>();
        int answer = 0;
        
        // n행move열: move가 고정인 채로 행을 돌아야한다.
        for(int i=0; i<moves.length; i++){  // 뽑기(열)
            int move = moves[i]-1;        
            
            for(int j=0; j<board.length; j++){  //행
                if(board[j][move] != 0){
                    int selectedOne = board[j][move];
                    if(!basket.isEmpty()){
                       int top = basket.peek();
                       // 2연속일 경우 버스트
                        if(top == selectedOne){
                            basket.pop();
                            answer += 2;
                        }else{
                            basket.push(selectedOne);
                        }
                        
                    }else{
                        // 바구니가 비었을 경우
                        basket.push(selectedOne);
                    }
                    
                    board[j][move] = 0;	//인형을 뽑았기 때문에 뽑기판에서 없애기
                    break;
                }
            }// 안쪽 for
        }//바깥 for
            
        
        return answer;
    }
}