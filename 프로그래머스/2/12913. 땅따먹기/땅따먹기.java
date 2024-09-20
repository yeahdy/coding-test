import java.util.*;

class Solution {
    int solution(int[][] land) {
        for(int i=1; i<land.length; i++){
            land[i][0] += maxLand(land[i-1][1],land[i-1][2],land[i-1][3]);
            land[i][1] += maxLand(land[i-1][0],land[i-1][2],land[i-1][3]);
            land[i][2] += maxLand(land[i-1][0],land[i-1][1],land[i-1][3]);
            land[i][3] += maxLand(land[i-1][0],land[i-1][1],land[i-1][2]);
        }
        
        int maxScore = 0;
        for(int i=0; i<land[0].length; i++){
            maxScore = Math.max(maxScore,land[land.length-1][i]);
        }
        return maxScore;
    }
    
    private int maxLand(int first, int second, int third){
        return Math.max(Math.max(first,second),third);
    }
}