import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println(new Main().getMinTime());
    }

    // 숫자 1 = 2초 -> 1씩 증가
    // 1. 문자를 숫자로 변경하기
    // 2. 숫자에 맞는 초 구하기 (숫자 + 1)
    // 3. 초를 모두 더해서 출력하기
    int getMinTime(){
        Scanner sc = new Scanner(System.in);
        String[] text = sc.next().split("");    //WA
        int minTime = 0;

        for(String oneChar :text){
            int number = textToNumber(oneChar);
            minTime += number + 1;
        }

        return minTime;
    }

    // W 또는 A
    private int textToNumber(String oneChar){
        int ascii = oneChar.charAt(0);

        //A~C
        if(65 <= ascii && 67 >= ascii){
            return 2;
        }
        //D~F
        if(68 <= ascii && 70 >= ascii){
            return 3;
        }
        //G~I
        if(71 <= ascii && 73 >= ascii){
            return 4;
        }
        //J~L
        if(74 <= ascii && 76 >= ascii){
            return 5;
        }
        //M~O
        if(77 <= ascii && 79 >= ascii){
            return 6;
        }
        //P~S
        if(80 <= ascii && 83 >= ascii){
            return 7;
        }
        //T~V
        if(84 <= ascii && 86 >= ascii){
            return 8;
        }
        //W~Z
        if(87 <= ascii && 90 >= ascii){
            return 9;
        }

        return 0;
    }

}
