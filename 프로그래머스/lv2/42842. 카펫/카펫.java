class Solution {
    /*
	 가로 >= 세로
	 갈색 10개: 노란색과 안 붙은 면 4개	노란색 2개: 6면		
	 갈색 8개: 노런색과 안붙은 면 4개		노란색 1개: 4면		
	 갈색 12개: 노란색과 안 붙은 면 4개	노란색 4개: 8면			 
	*/
	/*
	 Point! 전체갯수의 약수들 중 조건에 부합하는 것만 식별하기
	 ex) 전체 합 24 의 약수: (1,48)/(2,24)/(3,16)/(4,12)/(6,8)
	 조건1. 최소 노란색의 갯수가 1 이므로 이때 전체 가로,세로의 최소 길이는 각 3이다.
			>> (3,16)/(4,12)/(6,8)
	 조건2. 전체 가로-2 * 세로-2 = (센터에 오기 위한)노란색의 갯수
			>> (16-2)*(3-2)	!= 24 
			>> (6-2)*(8-2)	== 24
	*/
    
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown+yellow, limit = (int)Math.sqrt(total);
        
        // 약수 구하기
        for(int i=3; i<=limit; i++) {
        	if(total%i == 0) {
        		int length=total/i ,width= i;	// 가로 >= 세로
        		int calc = (length-2) * (width-2);
        		if(calc == yellow) {
        			answer[0]= length;
        			answer[1]= width;
        		}
        		
        	}
        }
        
        return answer;
    }
}