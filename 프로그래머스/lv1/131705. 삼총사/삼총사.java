class Solution {
    public int solution(int[] number) {
        int answer = 0, count = 0,length= number.length;
        
        for(int i=0; i<length; i++) {
        	for(int j=i+1; j<length; j++) {
        		for(int z= j+1; z<length; z++) {
        			answer = number[i]+number[j]+number[z];
        			if(answer == 0) {
        				count++;
        			}
        			
        		}
        	}
        }
        
        return count;
    }
}