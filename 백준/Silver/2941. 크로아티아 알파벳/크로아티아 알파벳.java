import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println(new Main().getCroatiaCount());
    }

    // 1. 주어진 값에서 크로아티아 알파벳을 모두 "@"로 변환한다.
    // 2. 문자열의 갯수를 카운팅한다.
    int getCroatiaCount() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine();
        String[] croatiaAlphabets = {"c=","c-","dz=","d-","lj","nj","s=","z="};

        for(String alphabet  : croatiaAlphabets){
            value = value.replace(alphabet,"@");
        }

        return value.length();
    }

}
