import java.util.*;

class Solution {
    static class Participant {
        int startTime;
        int endTime;
        int counseling;
        int type;
        public Participant(int startTime, int counseling, int type) {
            this.startTime = startTime;
            this.endTime = startTime+counseling;
            this.counseling = counseling;
            this.type = type;
        }
    }

    static List<Participant> participantList = new ArrayList<>();
    static int solution(int k, int n, int[][] reqs) {
        for(int [] r : reqs){
            participantList.add(new Participant(r[0],r[1],r[2]));
        }

        int[] typeMento = new int[k+1];
        Arrays.fill(typeMento,1);   //유형별로 최소 1명의 상담원 배치
        int remainingMento = n-k;

        while(remainingMento > 0){
            int minTime = Integer.MAX_VALUE;
            int bestDivision = -1;
            //임의로 상담원을 배치해서 최적의 상담원 배치 도출하기
            for(int i=1; i<typeMento.length; i++){
                typeMento[i]++;
                int waitingTime = calcWaitingTime(typeMento);

                //상담원 배정시 유형별로 가장 적은 대기 시간일 경우 유형 저장하기
                minTime = Math.min(minTime, waitingTime);
                if (minTime == waitingTime) {
                    bestDivision = i;
                }
                typeMento[i]--;
            }

            typeMento[bestDivision]++;
            remainingMento--;
        }

        return calcWaitingTime(typeMento);
    }

    //유형별 대기 시간의 총합 계산
    private static int calcWaitingTime(int[] typeMento){
        int totalWaitingTime = 0;

        for(int type=1; type<typeMento.length; type++){
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int waitingTime = 0;
            //유형별로 계산
            for(Participant participant : participantList){
                if(participant.type != type){
                    continue;
                }
                if(pq.size() < typeMento[type]){
                    pq.offer(participant.endTime);
                    continue;
                }

                int minEndTime = pq.poll();
                //대기시간 계산: 최소 상담종료시간 - 현재 사람의 상담 시작시간
                waitingTime += Math.max(0,minEndTime-participant.startTime);
                //현재 사람의 상담이 종료되는 가장 빠른 시간
                int fastEndTime = Math.max(minEndTime,participant.startTime)+participant.counseling;
                pq.offer(fastEndTime);
            }

            totalWaitingTime += waitingTime;
        }

        return totalWaitingTime;
    }
}