import java.util.*;

//user_id 배열의 크기는 1 이상 8 이하 > 8!
class Solution {
    HashSet<String> set = new HashSet<>();
    String[] userIds;
    String[] bannedIds;
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        for(int i=0; i<banned_id.length; i++){
            banned_id[i] = banned_id[i].replace('*','.');
        }
        userIds = user_id;
        bannedIds = banned_id;
        visited = new boolean[user_id.length];

        permutation(0, "");
        return set.size();
    }

    //유저 수(n) 중에서 제재 수(r) 만큼 뽑기
    private void permutation(int index, String ids){
        //r만큼 다 뽑았을 경우
        if(index == bannedIds.length){
            String[] combination = ids.split("\\s");
            Arrays.sort(combination);

            StringBuilder bannedCombination = new StringBuilder();
            for (String id : combination) {
                bannedCombination.append(id);
            }
            set.add(bannedCombination.toString());
            return;
        }

        for(int i=0; i<userIds.length; i++){
            //이미 방문했거나 제재 목록과 형식 일치하지 않을 경우 패스
            if(visited[i] || !userIds[i].matches(bannedIds[index])){
                continue;
            }
            visited[i] = true;
            permutation(index+1, ids + " " + userIds[i]);
            visited[i] = false;
        }
    }
    //제재 목록과 일치하는 조합일 경우(문자열 일치) 카운팅
        //문자열 일치 확인방법? matches 정규식 사용
        //제재 문자열에 1개 이상 같을 경우 어떻게 구별할건지?
            //조합이 아닌 순열로 모든 경우에 대해 매칭될 수 있도록 배열 만들기
            //원소가 같은 배열은 카운팅 하지않도록 Set 사용
}


