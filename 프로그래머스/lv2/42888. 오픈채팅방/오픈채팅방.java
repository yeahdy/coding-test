import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    public final String ENTER_STR = "##님이 들어왔습니다.", LEAVE_STR = "##님이 나갔습니다.";
    public String[] solution(String[] record) {
        Map<String,String> chatMap = new LinkedHashMap<>();	//Map<아이디,문장>
		Map<String,String> userMap = new HashMap<>();	//Map<아이디,닉네임>
        String[] answer = null;	// 전체 문장 출력
        String userInfo = "";
        
        // 유저와 채팅 저장
        for(int i=0; i<record.length; i++) {
        	userInfo = record[i];
        	// 0:키워드, 1:아이디, 2:닉네임
        	String[] info = userInfo.split("\\s");
        	String keyword=info[0], id= info[1];
			String msg = getMessage(keyword);
        	
        	if(!msg.isEmpty()) {	// enter과 leave일 경우에만 추가
        		chatMap.put(id+"_"+i, msg);
        	}
        	
        	// 아이디-닉네임 저장
        	if(!keyword.equalsIgnoreCase("Leave")) {	
        		userMap.put(id, info[2]);        		        		        		
        	}
        }
        
        //닉네임 변경
        System.out.println("size : "+chatMap.size());
        answer = new String[chatMap.size()];
        int i=0;
        for(Map.Entry<String, String> chatEnt : chatMap.entrySet()) {
        	String chat = chatEnt.getValue(), id = chatEnt.getKey().substring(0,chatEnt.getKey().indexOf("_"));
        	chat = chat.replace("##", userMap.get(id));
        	answer[i] = chat;
        	i++;
        }
        
        return answer;
    }
    
    
    // 키워드 
	private String getMessage(String keyword) {
		switch(keyword) {
		case "Enter":
			return ENTER_STR;
		case "Leave":
			return LEAVE_STR;
		default:	//Change
			return "";
		}
		
	}
}