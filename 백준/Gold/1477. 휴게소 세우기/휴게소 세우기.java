import java.util.*;
import java.io.*;

public class Main {
    static int[] location;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split("\\s");
        int N = Integer.parseInt(first[0]);
        int M = Integer.parseInt(first[1]);
        int L = Integer.parseInt(first[2]);

        String[] second = br.readLine().split("\\s");
        location = new int[N+2];
        location[0] = 0;
        for(int i=1; i<N+1; i++){
            location[i] = Integer.parseInt(second[i-1]);
        }
        location[N+1] = L;
        Arrays.sort(location);

        System.out.println(getLocation(M));
    }

    static int getLocation(int M){
        int left = 1;
        int right = location[location.length-1];
        while(left<=right){
            int mid = (left+right)/2;

            int buildCount = getBuildCount(mid);
            //"최대 구간"의 "최소값"이라는 최적의 값을 찾아야함
            if(buildCount > M){
                //left가 커짐 == middle 값이 커지면,
                //같은 거리 내에 설치할 수 있는 휴게소가 적어지기 때문에 휴게소 사이 간격이 커짐
                left = mid + 1;
            }else{
                //right가 작아짐 == middle 값이 작아지면,
                //같은 거리 내에 설치할 수 있는 휴게소가 많아지기 때문에 휴게소 사이 간격이 작아짐
                right = mid - 1;
            }
        }
        return left;
    }

    private static int getBuildCount (int mid){
        int build = 0;
        for(int i=1; i<location.length; i++){
            int distance = location[i] - location[i-1];
            build += distance/mid;
            //나누어 떨어지면 마지막 휴게소는 이미 휴게소가 있는 위치에 설치된다는 의미로 해당 구간은 휴게소 설치 필요없음
            if(distance%mid == 0){
                build--;
            }
        }
        return build;
    }

}