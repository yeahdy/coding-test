import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//다익스트라 최단거리 구하기
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().getMinDistance());
    }

    class Shortcut {
        int start;
        int end;
        int distance;

        public Shortcut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    int getMinDistance() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] distanceInfo = br.readLine().split("\\s");
        int shortCutCount = Integer.parseInt(distanceInfo[0]);
        int roadDistance = Integer.parseInt(distanceInfo[1]);

        //지름길 정보 추출하기
        List<Shortcut> shortcutList = new ArrayList<>();
        for(int i=0; i<shortCutCount; i++){
            String data = br.readLine();
            Shortcut shortcut= getShortcutInfo(data, roadDistance);
            if (shortcut != null) {
                shortcutList.add(shortcut);
            }
        }

        //1. 전체 구간 모델링: 거라를 기록하는 전체 위치
        int[] totalDistance = new int[roadDistance + 1];
        //2. 최단 경로 초기화
        Arrays.fill(totalDistance, Integer.MAX_VALUE);  //무한값을 기준으로 최소값을 비교하면 항상 최소값으로 갱신되기 위한 설정
        totalDistance[0] = 0;   //시작 위치의 거리는 0
        int current = 0;
        int idx = 0;

        //NOTE: 첫 구간부터 마지막 구간까지 순서대로 구간을 움직이기 때문에 정렬이 중요
        //★지름길 리스트를 시작 위치 기준으로 정렬★ 
        Collections.sort(shortcutList, Comparator.comparingInt(s -> s.start));

        //지름길: 현재 구간과 지름길의 시작구간과 같을 때
        //고속도로: 현재 구간에 맞는 지름길이 없을 때, 지름길을 모두 다 확인했을 경우
        while(current<roadDistance){
            if(idx == shortcutList.size()){
                //현재 위치에서 인접한 거리인 다음 구간을 확인하고, 다음 값과 새로운 값을 비교해서 최소값으로 갱신한다.
                totalDistance[current+1] = Math.min(totalDistance[current+1], totalDistance[current]+1);
                current++;
                continue;
            }

            Shortcut shortcut = shortcutList.get(idx);
            if(current == shortcut.start){  //해당 지름길의 끝 구간에 최소 거리의 거리를 기록한다.
                // 현재 지름길까지의 구간에 기존의 값과 새로운 값을 비교해서 최소 값으로 갱신
                //- distance[shortcut.end]: 기존에 저장된 도착 위치까지의 거리
                //- distance[current] + shortcut.distance: 현재 위치에서 지름길을 통해 도착 위치까지의 거리
                totalDistance[shortcut.end] = Math.min(totalDistance[shortcut.end], totalDistance[current] + shortcut.distance);
                idx++;
            }else{
                totalDistance[current+1] = Math.min(totalDistance[current+1], totalDistance[current]+1);
                current++;
            }
        }

        return totalDistance[roadDistance]; //마지막 위치의 거리
    }

    private Shortcut getShortcutInfo(String data, int roadDistance){
        String[] shortcutInfo = data.split("\\s");
        int shortStart= Integer.parseInt(shortcutInfo[0]);
        int shortEnd= Integer.parseInt(shortcutInfo[1]);
        int shortDistance= Integer.parseInt(shortcutInfo[2]);

        if(shortEnd > roadDistance){   //지름길 도착길이가 전채 고속도로보다 클 경우 제외
            return null;
        }
        if(shortEnd - shortStart < shortDistance){   //NOTE: 지름길 길이가 지름길 거리보다 멀 경우 제외
            return null;
        }
        return new Shortcut(shortStart, shortEnd, shortDistance);
    }

}
