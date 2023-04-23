import java.util.Arrays;

class Solution {
    public boolean solution(String[] phoneBook) {
        // 1. phoneBook 오름차순 정렬
        Arrays.sort(phoneBook);

        // 1중 Loop을 돌며 앞 번호가 뒷 번호의 접두어인지 확인
        for (int i = 0; i < phoneBook.length - 1; i++)
            if (phoneBook[i + 1].startsWith(phoneBook[i]))
                return false;
        
        return true;
    }
}