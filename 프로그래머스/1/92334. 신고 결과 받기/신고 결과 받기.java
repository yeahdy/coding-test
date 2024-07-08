import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        //사용자 초기화
        for(String id : id_list){
            reportMap.put(id, new User(id));
        }
        //신고정보 정제하기
        for(String info : report){
            String[] reportInfo = info.split(" ");
            String id = reportInfo[0];
            String reportedId = reportInfo[1];

            //신고하기
            User user = reportMap.get(id);
            if(user.isReport(reportedId)){
                //신고 당한 횟수 카운팅
                User reportedUser = reportMap.get(reportedId);
                reportedUser.beReported();
            }
        }

        //정지 당하는 계정 찾기: k번 넘게 신고 당한 계정은 정지
        List<String> stoppedIdList = new ArrayList<>();
        for(User user : reportMap.values()){
            if(k <= user.reportedCount){
                stoppedIdList.add(user.id);
            }
        }

        //NOTE: 신고한 계정이 정지되었는지 카운팅
        Set<String> stoppedIdSet = new HashSet<>(stoppedIdList);
        int idx = 0;
        for(User user : reportMap.values()){
            Set<String> reportSet = new HashSet<>(user.reportList);
            reportSet.retainAll(stoppedIdSet);

            answer[idx] = reportSet.size();
            idx++;
        }
        return answer;
    }
    
    LinkedHashMap<String, User> reportMap = new LinkedHashMap<>();  //저장순서 보장
    class User{
        String id;
        private List<String> reportList = new ArrayList<>();  //신고한 Id
        int reportedCount;  //신고당한 횟수

        public User(String id) {
            this.id = id;
        }

        boolean isReport (String reportId){
            if(!reportList.contains(reportId)){
                reportList.add(reportId);
                return true;
            }
            return false;
        }
        void beReported (){
            reportedCount++;
        }
    }

    
}