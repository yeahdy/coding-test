import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);

        int distance = 0;
        while(!queue.isEmpty()){
            for(int i=0; i<queue.size(); i++){
                String current = queue.poll();
                //단어를 찾았을 경우 지금까지 계산된 최단거리 반환
                if(current.equals(target)){
                    return distance;
                }

                for(int j=0; j<words.length; j++){
                    //인접한 단어 탐색
                    int different = 0;
                    String w = words[j];
                    for(int k=0; k<w.length(); k++){
                        if(current.charAt(k) != w.charAt(k)){
                            different++;
                        }
                    }

                    //방문하지 않은 인접한 단어일 경우
                    if(!visited[j] && different == 1){
                        visited[j] = true;  //인접한 단어 방문처리
                        queue.offer(w);
                    }
                }
            }
            distance++;
        }
        return 0;
    }
}  