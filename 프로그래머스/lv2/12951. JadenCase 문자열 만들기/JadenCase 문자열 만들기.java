import java.util.*;

class Solution {
    public String solution(String s) {
        if(s.equals(" ")) return "";
        String answer = "";
        
        String[] JadenCaseList = s.split("\\s");
        for(String JadenCase : JadenCaseList){
            if(JadenCase.isEmpty()){
                answer += " ";   //공백문자열은 더하기
                continue;
            }
            String restCase= JadenCase.substring(1).toLowerCase();    //or
            String upperChar= JadenCase.toUpperCase().charAt(0)+""; //F
            
            answer += upperChar + restCase +" ";
        }
        String lastChar= s.charAt(s.length()-1) + "";
        if(lastChar.equals(" ")) answer += lastChar;
        
        return answer.substring(0,answer.length()-1);
    }
}