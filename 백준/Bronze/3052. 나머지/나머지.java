import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
       System.out.println(Main.remainder());
    }

    static int remainder () throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();
        for (int i= 0; i < 10; i++){
            int value = Integer.parseInt(br.readLine());
            set.add(value % 42);
        }
        br.close();
        return set.size();
    }

}
