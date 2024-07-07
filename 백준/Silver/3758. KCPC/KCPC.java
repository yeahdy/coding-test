import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println(new Main().getTeamRanking());
    }


    //Team: 제출횟수, 전체점수, 마지막 로그번호, <제출한 문제번호, 최고점수>
    class Team{
        private int submission;
        private int totalScore;
        private int lastLogNo;
        private HashMap<Integer,Integer> questionInfo = new HashMap<>();

        public Team(int logNo) {
            this.submission++;
            this.lastLogNo = logNo;
        }

        //팀기록 업데이트
        public void updateTeamHistory(int logNo){
            this.submission++;
            this.lastLogNo = logNo;
        }

        //문제 추가
        public void createQuestion(int questionNo, int score){
            questionInfo.put(questionNo, score);
            this.totalScore += score;
        }
        //문제 업데이트
        public void updateQuestion(int questionNo,int originalScore, int score){
            if(originalScore < score){
                questionInfo.put(questionNo, score);
                this.totalScore -= originalScore;   //기존에 더한 점수 제거
                this.totalScore += score;   //신규 점수로 업데이트
            }
        }
        //문제의 점수 가져오기
        public Integer getQuestionScore(int questionNo){
            Integer score = questionInfo.get(questionNo);
            if(score != null){
                return score;
            }
            return null;
        }

        public int getTotalScore(){
            return totalScore;
        }

        public int getSubmission() {
            return submission;
        }

        public int getLastLogNo(){
            return lastLogNo;
        }

        @Override
        public String toString() {
            return "Team{" +
                    "submission=" + submission +
                    ", totalScore=" + totalScore +
                    ", lastLogNo=" + lastLogNo +
                    '}';
        }
    }

    HashMap<Integer,Team> teamMap = new HashMap<>(); //<팀ID, Team>

    String getTeamRanking() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] contestCount = new String[Integer.parseInt(br.readLine())];    //테스트 데이터 수

        StringBuilder totalRank = new StringBuilder();
        for(int i=0; i<contestCount.length; i++){
            // 대회정보 추출하기 (팀의 개수 n, 문제의 개수 k, 팀ID t 로그 엔트리수 m)
            String[] contestData = br.readLine().split(" ");
            int totalTeam = Integer.parseInt(contestData[0]);   //팀 수
            int questionCount = Integer.parseInt(contestData[1]);   //문제 갯수
            int myTeamId = Integer.parseInt(contestData[2]);  //내 팀ID
            int totalLog = Integer.parseInt(contestData[3]);//전체로그 수

            //로그 엔트리 분석
            int logNo = 0;  //문제 제출 로그순서
            for(int j=0; j<totalLog; j++){
                //팀별 문제 정보 추출하기(팀ID, 문제번호, 획득한 점수)
                String[] logData = br.readLine().split(" ");
                int teamId = Integer.parseInt(logData[0]);
                int questionNo = Integer.parseInt(logData[1]);
                int score = Integer.parseInt(logData[2]);
                logNo++;

                //팀 찾기
                Team team = teamMap.get(teamId);
                if(team == null){
                    teamMap.put(teamId,new Team(logNo));
                    team = teamMap.get(teamId);
                }else{
                    team.updateTeamHistory(logNo);  //기존의 있는 팀은 팀기록 업데이트
                }

                //팀별로 문제와 점수를 저장한다. (★이때 제출 한 문제중에서 가장 높은 점수를 저장한다.)
                Integer originalScore = team.getQuestionScore(questionNo);
                if(originalScore == null){
                    team.createQuestion(questionNo, score);
                }else{
                    team.updateQuestion(questionNo, originalScore, score);
                }
            }

            //NOTE: "나의 팀"의 순위 찾기
            // > score를 기준으로 몇등인지 찾는다.
            // > 동점일 경우 1. submission이 적으면 랭킹+ 2.lastLogNo이 더 적으면 랭킹+
            Team myTeam = teamMap.get(myTeamId);
            int myScore = myTeam.getTotalScore();
            int mySubmission = myTeam.getSubmission();
            int myLastLogNo = myTeam.getLastLogNo();
            int myRank = 0;
            for(Team team : teamMap.values()){
                if(team.getTotalScore() > myScore){
                    myRank++;
                }else if(team.getTotalScore() == myScore){
                    if(team.getSubmission() < mySubmission){
                        myRank++;
                    }else if(team.getSubmission() == mySubmission){
                        if(team.getLastLogNo() < myLastLogNo){
                            myRank++;
                        }
                    }
                }
            }

            totalRank.append(myRank+1).append("\n");
            teamMap = new HashMap<>();  // 초기화
        }

        if (totalRank.length() > 0) {
            totalRank.setLength(totalRank.length() - 1);
        }
        return totalRank.toString();
    }

}
