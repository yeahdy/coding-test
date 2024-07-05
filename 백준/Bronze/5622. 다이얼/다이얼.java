import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().getMinTime());
    }

    // 숫자 1 = 2초 -> 1씩 증가
    // 1. 문자를 숫자로 변경하기
    // 2. 숫자에 맞는 초 구하기 (숫자 + 1)
    // 3. 초를 모두 더해서 출력하기
    int getMinTime() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] text = br.readLine().split("");    //WA
        int minTime = 0;

        for (String oneChar : text) {
            int number = textToNumber(oneChar);
            minTime += number + 1;
        }

        return minTime;
    }

    // W 또는 A
    private int textToNumber(String oneChar) {
        int ascii = oneChar.charAt(0);

        //A~C
        if (67 >= ascii) {
            return 2;
        }
        //D~F
        if (70 >= ascii) {
            return 3;
        }
        //G~I
        if (73 >= ascii) {
            return 4;
        }
        //J~L
        if (76 >= ascii) {
            return 5;
        }
        //M~O
        if (79 >= ascii) {
            return 6;
        }
        //P~S
        if (83 >= ascii) {
            return 7;
        }
        //T~V
        if (86 >= ascii) {
            return 8;
        }
        //W~Z
        if (90 >= ascii) {
            return 9;
        }

        return 0;
    }

}
