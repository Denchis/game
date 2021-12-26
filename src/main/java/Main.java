import game.Game;
import game.View;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static boolean repeatCheck(String[] args){
        for (int i = 0; i < args.length; i++){
            for(int j = 0; j < args.length;j++){
                if (i != j && args[i].equals(args[j])){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        if (args.length % 2 != 1 || repeatCheck(args) || args.length < 3){
            System.out.println("The number of arguments must be odd >=3 and must not be repeated");
            System.out.println("Example: 1 2 3 4 5");
            System.exit(0);
        }
        Game g = new Game(args);
        g.start();


    }
}
