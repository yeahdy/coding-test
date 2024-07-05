import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println(new Main().getChess());
    }

    String getChess() throws IOException {
        int[] pieceArr = {1, 1, 2, 2, 2, 8};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] valueArr = br.readLine().split(" ");
        String chessPiece = "";

        for(int i=0; i < pieceArr.length; i++){
            int piece = pieceArr[i];
            int value = Integer.parseInt(valueArr[i]);

            int result = piece - value;
            chessPiece += " "+result;
        }

        return chessPiece.trim();
    }

}
