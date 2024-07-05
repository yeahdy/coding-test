import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args) throws IOException{
        System.out.println(new Main().getRoomCount());
    }

    // 2~7 > 6개
    // 8~19 > 12개
    // 20~37 > 18개
    // 38~61 > 24개
    int getRoomCount() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(br.readLine());
        if(value == 1){
            return 1;
        }

        int roomCount = 1;
        int total= 1;
        while(true){
            total += (6*roomCount);
            if(value <= total){
                break;
            }
            roomCount++;
        }
        return roomCount +1;
    }

}
