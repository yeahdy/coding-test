import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println(new Main().getMaxPeople());
    }

    int getMaxPeople() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] value = br.readLine().split("\\s");

        int w = Integer.parseInt(value[0]); //강의실 세로
        int h = Integer.parseInt(value[1]); //강의실 가로
        int n = Integer.parseInt(value[2]); //간격 세로
        int m = Integer.parseInt(value[3]); //간격 가로

        //강의실 세로만 생각했을 때 강의실의 최대 세로수까지 사람1명+세로간격 다음에 사람1명+세로간격 ... 이 반복된다.
        //사람1명+세로가격을 했을 때 남은 강의실의 세로 공간은 w-(1+n) 이다.
        //남은 강의실의 공간에 사람1명+세로간격을 반복해서 넣으면, w/(n+1) 했을 때 몫이 거리두기의 갯수가 된다.
        
        //그런데 세로공간에 최소 사람 1명은 항상 채워지기 때문에 실제 세로공간은 전체공간-1 이다.
        // -> (w-1)/(n+1)
        
        //최소 한명의 사람이 있기 때문에 +1 을 해준다. -> ((w-1)/(n+1))+1

        int length = ((w-1)/(n+1))+1;
        int transverse = ((h-1)/(m+1))+1;
        return length*transverse;
    }
}