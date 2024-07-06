import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//슬라이드 윈도우
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().getMinChangeCount());
    }

    int getMinChangeCount() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine();
        int aCount = 0;
        for(String v :value.split("")){
            if("a".equals(v)){
                aCount++;
            }
        }

        int minBCount = Integer.MAX_VALUE;  //기준을 가장 최대값을 잡으면 다음에 비교하는 값은 항상 최소값이 된다.
        for(int i=0; i<value.length(); i++){
            int bCount = 0;
            for(int j=i; j<aCount+i; j++){
                //문자열은 원형으로 이어져 있기 때문에 인덱스 끝까지 갔을 경우 다시 첫번째 인덱스부터 길이에 포함한다.
                char current = value.charAt(j%value.length());
                if(current == 'b'){
                    bCount++;
                }
            }

            minBCount = Math.min(minBCount, bCount);
        }

        return minBCount;
    }

    //NOTE: 문자열이 연속되어 있는지 어떻게 알수있지?
    // 목표: a를 연속적으로 만드는 것
    // > a의 갯수만큼 연속적으로 a가 있다는 것은 전체적인 문자열에서도 a가 연속적으로 배치되어 있는것.
    // > 따라서 a의 갯수만큼 a가 연속적으로 있는지 체크하면 됨
    // > 바꾼 문자열을 출력하는게 아니기 때문에 a와b를 직접 교환할 필요가 없다! b가 어느 위치에서 자리를 교환하는지는 알 필요 X

    //NOTE: 왜 b의 최소값이 최소 교환횟수가 될까?
    // ex) babbab 일 경우 a의 갯수는 2개
    // > 2개의 a가 연속해서 있으면 됨.
    // > 'ba'bbab 에서 b자리에 a가 오면 연속적인 a 완성! b가 어느 위치의 a와 교환할지는 문제에서 요구하는 답이 아님!
    // > b'ab'bab 에서 b의 갯수는 1
    // > ba'bb'ab 에서 b의 갯수는 2
    // > bab'ba'b 에서 b의 갯수는 1
    // > babb'ab' 에서 b의 갯수는 1
    // > b'abba'b 에서 b의 갯수는 2
    // > 따라서 b의 최소 갯수인 1 이 최소 교환갯수가 된다.

    //초기 쓸모없는 고민
    //어떤 조건일 때 문자열의 자리를 교체하면 될까?
    //문자열이 최대 1000자인데 어떻게 옮기지?
}
