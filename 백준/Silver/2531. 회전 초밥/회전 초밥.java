import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 알고리즘: 투 포인터
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] first = sc.nextLine().split("\\s");

        int N = Integer.parseInt(first[0]); //접시의 수
        int d = Integer.parseInt(first[1]); //초밥의 가짓수
        int k = Integer.parseInt(first[2]); //연속해서 먹는 접시의 수
        int c = Integer.parseInt(first[3]); //쿠폰번호(=초밥종류)
//        Set<Integer> beltSet = new HashSet<>();
//        for(int i=0; i<N; i++){
//            beltSet.add(Integer.parseInt(sc.nextLine()));
//        }
//        belt = beltSet.toArray(new Integer[0]);

        int[] sushi = new int[N];
        for(int i = 0 ; i < N ; i++) {
            sushi[i] = Integer.parseInt(sc.nextLine());
        }

        int[] chk = new int[d+1];   // 먹은 초밥 종류 관련 배열
        chk[c] = 3001;  // 무료 초밥 종류

        int cnt = 1;    // 무료 초밥이 1개이므로

        // 1. 회전하지 않았을 때 초밥 종류 구하기
        for(int i = 0 ; i < k ; i++) {
            if(chk[sushi[i]] == 0)
                cnt++;

            chk[sushi[i]]++;
        }

        int max = cnt;

        // ⭐ 2. N-1번 회전을 투 포인터로 실행
        for(int i = 0 ; i < N-1 ; i++) {
            int sIdx = sushi[i];    // 처음 먹은 초밥 종류
            int eIdx = sushi[((i+k) < N) ? (i+k) : (i+k) % N];    // 마지막 + 1번째 먹을 초밥 종류

            // 처음 먹은 초밥 종류가 더 이상 없을 때
            if(--chk[sIdx] == 0)
                cnt--;

            // 마지막 +1번째 먹을 초밥이 처음 먹는 것일 때
            if(++chk[eIdx] == 1)
                cnt++;

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }

}
